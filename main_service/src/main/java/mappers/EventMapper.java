package mappers;

import dto.event.EventDetailedDto;
import dto.event.EventPatchDto;
import dto.event.EventPostDto;
import dto.event.EventShortDto;
import exception.NotFoundException;
import model.Category;
import model.Event;
import model.PublicationState;
import model.User;
import repository.CategoryRepository;
import repository.UserRepository;

import java.time.LocalDateTime;

public class EventMapper {

    private EventMapper() {}

    public static Event toModel(EventPostDto dto, Long initiator, CategoryRepository catRepo, UserRepository userRepo) {
        return Event.builder()
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
                .views(null)
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
            throw new NotFoundException("Не найдена Category id:" + id);
        }
        return category;
    }

    private static User matchUser(Long userId, UserRepository repo) {
        User user = repo.findById(userId).orElse(null);
        if (user == null) {
            throw new NotFoundException("Не найден User id: " + userId);
        }
        return user;
    }
}