package ru.practicum.ewm_ms.mappers;

import ru.practicum.ewm_ms.dto.event.EventDetailedDto;
import ru.practicum.ewm_ms.dto.event.EventPatchDto;
import ru.practicum.ewm_ms.dto.event.EventPostDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.model.Category;
import ru.practicum.ewm_ms.model.Event;
import ru.practicum.ewm_ms.model.PublicationState;
import ru.practicum.ewm_ms.model.User;
import ru.practicum.ewm_ms.repository.CategoryRepository;
import ru.practicum.ewm_ms.repository.UserRepository;
import ru.practicum.ewm_ms.util.Util;

import java.time.LocalDateTime;

public class EventMapper {

    private EventMapper() {}

    public static Event toModel(EventPostDto dto, Long initiator, CategoryRepository catRepo, UserRepository userRepo) {
        Event event = Event.builder()
                .annotation(dto.getAnnotation())
                .category(matchCategory(dto.getCategory(), catRepo))
                .confirmedRequests(0)
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
                .views(0L)
                .build();

        if (event.getRequestModeration() == null) {
            event.setRequestModeration(true);
        }
        return event;
    }

    public static Event toModel(EventPatchDto dto, CategoryRepository catRepo) {
        return Event.builder()
                .annotation(dto.getAnnotation())
                .category(matchCategory(dto.getCategory(), catRepo))
                .confirmedRequests(null)
                .createdOn(null)
                .description(dto.getDescription())
                .eventDate(DateTimeMapper.toDateTime(dto.getEventDate()))
                .id(dto.getId())
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

    public static EventShortDto toEventShortDto(Event event) {
        return EventShortDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .eventDate(DateTimeMapper.toString(event.getEventDate()))
                .id(event.getId())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

    public static EventDetailedDto toEventDetailedDto(Event event) {
        return EventDetailedDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .createdOn(DateTimeMapper.toString(event.getCreatedOn()))
                .description(event.getDescription())
                .eventDate(DateTimeMapper.toString(event.getEventDate()))
                .id(event.getId())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .location(event.getLocation())
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(DateTimeMapper.toString(event.getPublishedOn()))
                .requestModeration(event.getRequestModeration())
                .state(event.getState().toString())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
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