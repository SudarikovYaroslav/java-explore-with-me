package ru.practicum.ewm_ms.service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm_ms.dto.ParticipationDto;
import ru.practicum.ewm_ms.exception.ForbiddenException;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.mappers.ParticipationMapper;
import ru.practicum.ewm_ms.model.*;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.repository.ParticipationRepository;
import ru.practicum.ewm_ms.repository.UserRepository;
import ru.practicum.ewm_ms.service.ParticipationService;
import ru.practicum.ewm_ms.util.NotFoundMessageGen;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public List<ParticipationDto> getInfoAboutAllParticipation(Long userId) {
        checkIfUserExists(userId);
        List<Participation> participations = participationRepository.findAllByRequesterId(userId);
        return participations.stream().map(ParticipationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ParticipationDto addParticipationQuery(Long userId, Long eventId) {
        User user = checkIfUserExists(userId);
        Event event = checkIfEventExists(eventId);

        Participation participation = participationRepository
                .findByEventIdAndRequesterId(eventId, userId).orElse(null);
        if (participation != null) {
            return ParticipationMapper.toDto(participation);
        }
        if (event.getInitiator().getId().equals(userId)) {
            throw new ForbiddenException("User id:" + userId
                    + " the user cannot apply to participate in their own event");
        }
        if (!event.getState().equals(PublicationState.PUBLISHED)) {
            throw new ForbiddenException("you cannot take part in an unpublished event");
        }
        if (event.getParticipantLimit() != 0 && event.getConfirmedRequests() >= event.getParticipantLimit()) {
            throw new ForbiddenException("the limit of participants in the event has been reached");
        }

        Participation newParticipation = Participation.builder()
                .created(LocalDateTime.now())
                .event(event)
                .requester(user)
                .build();

        if (event.getRequestModeration().equals(false)) {
            newParticipation.setState(ApplicationState.APPROVED);
        } else {
            newParticipation.setState(ApplicationState.PENDING);
        }

        newParticipation = participationRepository.save(newParticipation);
        return ParticipationMapper.toDto(newParticipation);
    }

    @Override
    public ParticipationDto cancelParticipation(Long userId, Long requestId) {
        checkIfUserExists(userId);
        Participation participation = checkIfParticipationRequestExists(requestId);
        participationRepository.deleteById(requestId);
        return ParticipationMapper.toDto(participation);
    }

    private User checkIfUserExists(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new NotFoundException(NotFoundMessageGen.getUserNotFoundMessage(userId));
        }
        return user;
    }

    private Event checkIfEventExists(Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            throw new NotFoundException(NotFoundMessageGen.getEventNotFoundMessage(eventId));
        }
        return event;
    }

    private Participation checkIfParticipationRequestExists(Long requestId) {
        Participation par = participationRepository.findById(requestId).orElse(null);
        if (par == null) {
            throw new NotFoundException("Participation request with id=" + requestId +" was not found.");
        }
        return par;
    }
}
