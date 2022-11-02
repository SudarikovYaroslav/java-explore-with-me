package ru.practicum.ewm_ms.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ewm_ms.client.EventClient;
import ru.practicum.ewm_ms.dto.event.EventDetailedDto;
import ru.practicum.ewm_ms.dto.event.EventPatchDto;
import ru.practicum.ewm_ms.dto.event.EventPostDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.model.*;
import ru.practicum.ewm_ms.repository.CategoryRepository;
import ru.practicum.ewm_ms.repository.ParticipationRepository;
import ru.practicum.ewm_ms.repository.UserRepository;
import ru.practicum.ewm_ms.util.Util;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventMapper {

    public static Event toModel(EventPostDto dto, Long initiator, CategoryRepository catRepo, UserRepository userRepo) {
        Event event = Event.builder()
                .annotation(dto.getAnnotation())
                .category(matchCategory(dto.getCategory(), catRepo))
                .createdOn(LocalDateTime.now())
                .description(dto.getDescription())
                .eventDate(DateTimeMapper.toDateTime(dto.getEventDate()))
                .id(null)
                .initiator(matchUser(initiator, userRepo))
                .location(dto.getLocation())
                .paid(dto.getPaid())
                .participantLimit(dto.getParticipantLimit())
                .publishedOn(null)
                .requestModeration(dto.getRequestModeration())
                .state(PublicationState.PENDING)
                .title(dto.getTitle())
                .build();

        event.setRequestModeration(event.getRequestModeration() == null || event.getRequestModeration());
        return event;
    }

    public static Event toModel(EventPatchDto dto, CategoryRepository catRepo) {
        return Event.builder()
                .annotation(dto.getAnnotation())
                .category(matchCategory(dto.getCategory(), catRepo))
                .createdOn(null)
                .description(dto.getDescription())
                .eventDate(DateTimeMapper.toDateTime(dto.getEventDate()))
                .id(dto.getEventId())
                .initiator(null)
                .location(null)
                .paid(dto.getPaid())
                .participantLimit(dto.getParticipantLimit())
                .publishedOn(null)
                .requestModeration(null)
                .state(null)
                .title(dto.getTitle())
                .build();
    }

    public static EventShortDto toEventShortDto(Event event, EventClient client, ParticipationRepository repo) {
        return EventShortDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toDto(event.getCategory()))
                .confirmedRequests(repo.getConfirmedRequests(event.getId(), ParticipationState.CONFIRMED))
                .eventDate(DateTimeMapper.toString(event.getEventDate()))
                .id(event.getId())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(client.getViewsByEventId(event.getId()).getBody())
                .build();
    }

    public static EventDetailedDto toEventDetailedDto(Event event, EventClient client, ParticipationRepository repo) {
        EventDetailedDto dto = EventDetailedDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toDto(event.getCategory()))
                .confirmedRequests(repo.getConfirmedRequests(event.getId(), ParticipationState.CONFIRMED))
                .createdOn(DateTimeMapper.toString(event.getCreatedOn()))
                .description(event.getDescription())
                .eventDate(DateTimeMapper.toString(event.getEventDate()))
                .id(event.getId())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .location(event.getLocation())
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .requestModeration(event.getRequestModeration())
                .state(event.getState().toString())
                .title(event.getTitle())
                .views(client.getViewsByEventId(event.getId()).getBody())
                .build();

        if (event.getPublishedOn() != null) {
            dto.setPublishedOn(DateTimeMapper.toString(event.getPublishedOn()));
        }
        return dto;
    }

    private static Category matchCategory(Long id, CategoryRepository repo) {
        Category category = repo.findById(id).orElse(null);
        if (category == null) {
            throw new NotFoundException(Util.getCategoryNotFoundMessage(id));
        }
        return category;
    }

    private static User matchUser(Long userId, UserRepository repo) {
        User user = repo.findById(userId).orElse(null);
        if (user == null) {
            throw new NotFoundException(Util.getUserNotFoundMessage(userId));
        }
        return user;
    }
}