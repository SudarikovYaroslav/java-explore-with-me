package ru.practicum.ewm_ms.service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.practicum.ewm_ms.dto.ParticipationDto;
import ru.practicum.ewm_ms.dto.event.EventDetailedDto;
import ru.practicum.ewm_ms.dto.event.EventPatchDto;
import ru.practicum.ewm_ms.dto.event.EventPostDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;
import ru.practicum.ewm_ms.exception.ForbiddenException;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.http_client.EventClient;
import ru.practicum.ewm_ms.mappers.DateTimeMapper;
import ru.practicum.ewm_ms.mappers.EventMapper;
import ru.practicum.ewm_ms.mappers.ParticipationMapper;
import ru.practicum.ewm_ms.model.*;
import ru.practicum.ewm_ms.repository.CategoryRepository;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.repository.ParticipationRepository;
import ru.practicum.ewm_ms.repository.UserRepository;
import ru.practicum.ewm_ms.service.EventService;
import ru.practicum.ewm_ms.util.Util;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    public static final long HOURS_LEFT_BEFORE_EVENT = 2;
    public static final long HOURS_LEFT_AFTER_PUBLICATION = 1;

    private final ParticipationRepository participationRepo;
    private final CategoryRepository categoryRepo;
    private final EventRepository eventRepo;
    private final UserRepository userRepo;
    private final EventClient client;

    @Override
    public List<EventShortDto> getEvents(EventSearchParams params, String clientIp, String endpoint) {
        Sort sort = Sort.by(params.getSort().toString());
        Pageable pageable = PageRequest.of(params.getFrom() / params.getSize(), params.getSize(), sort);
        Specification<Event> specification = getSpecification(params, true);
        List<Event> events = eventRepo.findAll(specification, pageable).toList();
        addViewForEach(events);
        client.postHit(endpoint, clientIp);
        return events.stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventDetailedDto findEventById(Long id, String clientIp, String endpoint) {
        Event event = eventRepo.findById(id).orElse(null);
        if (event == null) {
            throw new NotFoundException(Util.getEventNotFoundMessage(id));
        }
        event = addView(event);
        client.postHit(endpoint, clientIp);
        return EventMapper.toEventDetailedDto(event);
    }

    @Override
    public List<EventShortDto> findEventsByInitiatorId(Long userId, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Event> events = eventRepo.findAllByInitiatorId(userId, pageable);
        return events.stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventDetailedDto patchEvent(Long userId, EventPatchDto dto) {
        Util.checkIfUserExists(userId, userRepo);
        Event event = Util.checkIfEventExists(dto.getId(), eventRepo);

        if (event.getState().equals(PublicationState.PUBLISHED)) {
            throw new ForbiddenException("Only pending or canceled events can be changed");
        }

        updateEvent(event, dto);
        if (event.getState().equals(PublicationState.CANCEL)) {
            event.setState(PublicationState.PENDING);
        }
        event = eventRepo.save(event);
        return EventMapper.toEventDetailedDto(event);
    }

    @Override
    public EventDetailedDto postEvent(Long userId, EventPostDto dto) {
        if (isEventDateOk(dto.getEventDate())) {
            throw new ForbiddenException("the event cannot be earlier than 2 hours from the current time");
        }
        Event event = EventMapper.toModel(dto, userId, categoryRepo, userRepo);
        event = eventRepo.save(event);
        return EventMapper.toEventDetailedDto(event);
    }

    @Override
    public EventDetailedDto findEventByIdAndOwnerId(Long userId, Long eventId) {
        Event event = eventRepo.findByIdAndInitiatorId(eventId, userId).orElse(null);
        if (event == null) {
            throw new NotFoundException(Util.getEventNotFoundMessage(eventId));
        }
        return EventMapper.toEventDetailedDto(event);
    }

    @Override
    public EventDetailedDto cancelEventByIdAndOwnerId(Long userId, Long eventId) {
        Event event = eventRepo.findByIdAndInitiatorId(eventId, userId).orElse(null);
        if (event == null) {
            throw new NotFoundException(Util.getEventNotFoundMessage(eventId));
        }
        if (!event.getState().equals(PublicationState.PENDING)) {
            throw new ForbiddenException("the event can only be canceled in the waiting state, current state: "
                    + event.getState());
        }
        event.setState(PublicationState.CANCEL);
        event = eventRepo.save(event);
        return EventMapper.toEventDetailedDto(event);
    }

    @Override
    public List<ParticipationDto> getInfoAboutEventParticipation(Long userId, Long eventId) {
        Event event = eventRepo.findByIdAndInitiatorId(eventId, userId).orElse(null);
        if (event == null) {
            throw new NotFoundException(Util.getEventNotFoundMessage(eventId));
        }
        List<Participation> participations = participationRepo.findAllByEventId(eventId);
        return participations.stream().map(ParticipationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ParticipationDto confirmParticipation(Long userId, Long eventId, Long reqId) {
        Event event = eventRepo.findByIdAndInitiatorId(eventId, userId)
                .orElseThrow(() -> new NotFoundException(Util.getEventNotFoundMessage(eventId)));

        if (event.getParticipantLimit() == 0 || !event.getRequestModeration()) {
            throw new ForbiddenException("Confirmation of the participation is not required");
        }
        if (event.getParticipantLimit().equals(event.getConfirmedRequests())) {
            throw new ForbiddenException("the limit of participants in the event has been reached");
        }

        Participation participation = participationRepo.findById(reqId)
                .orElseThrow(()-> new NotFoundException(Util.getParticipationNotFoundMessage(reqId)));

        if (participation.getState().equals(ParticipationState.CONFIRMED)) {
           throw new  ForbiddenException("the request for participation has already been confirmed");
        }

        participation.setState(ParticipationState.CONFIRMED);
        increaseConfirmedRequest(event);
        checkParticipationLimit(event);
        participation = participationRepo.save(participation);
        return ParticipationMapper.toDto(participation);
    }

    @Override
    public ParticipationDto rejectParticipation(Long userId, Long eventId, Long reqId) {
        eventRepo.findByIdAndInitiatorId(eventId, userId)
                .orElseThrow(() -> new NotFoundException(Util.getEventNotFoundMessage(eventId)));

        Participation participation = participationRepo.findById(reqId)
                .orElseThrow(()-> new NotFoundException(Util.getParticipationNotFoundMessage(reqId)));

        if (participation.getState().equals(ParticipationState.REJECT)) {
            throw new ForbiddenException("request for participation has already been rejected");
        }
        participation.setState(ParticipationState.REJECT);
        participation = participationRepo.save(participation);
        return ParticipationMapper.toDto(participation);
    }

    @Override
    public List<EventDetailedDto> findEventsByConditions(EventSearchParams params) {
        Pageable pageable = PageRequest.of(params.getFrom() / params.getSize(), params.getSize());
        Specification<Event> specification = getSpecification(params, false);

        List<Event> events = eventRepo.findAll(specification, pageable).toList();
        return events.stream().map(EventMapper::toEventDetailedDto).collect(Collectors.toList());
    }

    @Override
    public EventDetailedDto editEvent(Long eventId, EventPostDto dto) {
        Event editable = eventRepo.findById(eventId)
                .orElseThrow(()-> new NotFoundException(Util.getEventNotFoundMessage(eventId)));

        if (dto.getAnnotation() != null) {
            editable.setAnnotation(dto.getAnnotation());
        }
        if (dto.getCategory() != null) {
            editable.setCategory(Util.mapIdToCategory(dto.getCategory(), categoryRepo));
        }
        if (dto.getDescription() != null) {
            editable.setDescription(dto.getDescription());
        }
        if (dto.getEventDate() != null && isEventDateOk(dto.getEventDate())) {
            editable.setEventDate(DateTimeMapper.toDateTime(dto.getEventDate()));
        }
        if (dto.getLocation() != null) {
            editable.setLocation(dto.getLocation());
        }
        if (dto.getPaid() != null) {
            editable.setPaid(dto.getPaid());
        }
        if (dto.getParticipantLimit() != null) {
            editable.setParticipantLimit(dto.getParticipantLimit());
        }
        if (dto.getRequestModeration() != null) {
            editable.setRequestModeration(dto.getRequestModeration());
        }
        if (dto.getTitle() != null) {
            editable.setTitle(dto.getTitle());
        }
        editable = eventRepo.save(editable);
        return EventMapper.toEventDetailedDto(editable);
    }

    @Override
    public EventDetailedDto publishEvent(Long eventId) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(()-> new NotFoundException(Util.getEventNotFoundMessage(eventId)));

        if (!event.getState().equals(PublicationState.PENDING)) {
            throw new ForbiddenException("event must be in the publication waiting state");
        }
        if (!isMayPublish(event)) {
            throw new ForbiddenException("event date must be no earlier than an hour from the date of publication");
        }
        event.setPublishedOn(LocalDateTime.now());
        event.setState(PublicationState.PUBLISHED);
        event = eventRepo.save(event);
        return EventMapper.toEventDetailedDto(event);
    }

    @Override
    public EventDetailedDto rejectEvent(Long eventId) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(()-> new ForbiddenException(Util.getEventNotFoundMessage(eventId)));
        if (event.getState().equals(PublicationState.PUBLISHED)) {
            throw new ForbiddenException("not possible to reject a published event");
        }
        event.setState(PublicationState.REJECTED);
        event = eventRepo.save(event);
        return EventMapper.toEventDetailedDto(event);
    }

    private Specification<Event> getSpecification(EventSearchParams params, boolean publicRequest) {
        return  (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.getUserIds() != null) {
                for (Long userId : params.getUserIds()) {
                    predicates.add(criteriaBuilder.equal(root.get("initiator_id"), userId));
                }
            }
            if (publicRequest) {
                predicates.add(criteriaBuilder.equal(root.get("state"), PublicationState.PUBLISHED));
            } else {
                for (PublicationState state : params.getStates()) {
                    predicates.add(criteriaBuilder.equal(root.get("state"), state));
                }
            }
            if (null != params.getText()) {
                predicates.add(criteriaBuilder.like(root.get("annotation"), "%"+params.getText()+"%"));
                predicates.add(criteriaBuilder.like(root.get("description"), "%"+params.getText()+"%"));
            }
            if (null != params.getCategories() && !params.getCategories().isEmpty()){
                for (Long catId : params.getCategories()) {
                    predicates.add(criteriaBuilder.equal(root.get("category_id"), catId));
                }
            }
            if (null != params.getPaid()) {
                predicates.add(criteriaBuilder.equal(root.get("paid"), params.getPaid()));
            }
            if (null != params.getRangeStart()) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("published_on"), params.getRangeStart()));
            } else {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("published_on"), LocalDateTime.now()));
            }
            if (null != params.getRangeEnd()) {
                predicates.add(criteriaBuilder.lessThan(root.get("published_on"), params.getRangeEnd()));
            } else {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("published_on"), LocalDateTime.now()));
            }
            if (null != params.getOnlyAvailable() && params.getOnlyAvailable()) {
                predicates.add(criteriaBuilder.lessThan(root.get("participant_limit"), root.get("confirmed_Requests")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private void updateEvent(Event event, EventPatchDto update) {
        if (update.getAnnotation() != null) {
            event.setAnnotation(update.getAnnotation());
        }
        if (update.getCategory() != null) {
            Category category = Util.checkIfCategoryExists(update.getCategory(), categoryRepo);
            event.setCategory(category);
        }
        if (update.getDescription() != null) {
            event.setDescription(update.getDescription());
        }
        if (update.getEventDate() != null) {
            if (!isEventDateOk(update.getEventDate())) {
                throw new ForbiddenException("the event can be changed no later than 2 hours before the start");
            }
            event.setEventDate(DateTimeMapper.toDateTime(update.getEventDate()));
        }
        if (update.getPaid() != null) {
            event.setPaid(update.getPaid());
        }
        if (update.getParticipantLimit() != null) {
            event.setParticipantLimit(update.getParticipantLimit());
        }
        if (update.getTitle() != null) {
            event.setTitle(update.getTitle());
        }
    }

    private boolean isEventDateOk(String eventDate) {
        LocalDateTime date = DateTimeMapper.toDateTime(eventDate);
        return date.isAfter(LocalDateTime.now().plusHours(HOURS_LEFT_BEFORE_EVENT));
    }

    private Event addView(Event event) {
        long views = event.getViews() + 1;
        event.setViews(views);
        return eventRepo.save(event);
    }

    private void addViewForEach(List<Event> events) {
        for (Event event : events) {
            addView(event);
        }
    }

    private void increaseConfirmedRequest(Event event) {
        int confirmedRec = event.getConfirmedRequests() + 1;
        event.setConfirmedRequests(confirmedRec);
        eventRepo.save(event);
    }

    private void checkParticipationLimit(Event event) {
        if (event.getParticipantLimit().equals(event.getConfirmedRequests())) {
            List<Participation> participations = participationRepo
                    .findAllByEventIdAndState(event.getId(), ParticipationState.PENDING);

            for (Participation par : participations) {
                par.setState(ParticipationState.REJECT);
                participationRepo.save(par);
            }
        }
    }

    private boolean isMayPublish(Event event) {
        return event.getEventDate().isAfter(LocalDateTime.now().plusHours(HOURS_LEFT_AFTER_PUBLICATION));
    }
}