package ru.practicum.ms.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ms.dto.comment.CommentPostDto;
import ru.practicum.ms.dto.comment.CommentResponseDto;
import ru.practicum.ms.dto.event.EventShortDto;
import ru.practicum.ms.dto.user.UserShortDto;
import ru.practicum.ms.model.Comment;
import ru.practicum.ms.model.Event;
import ru.practicum.ms.model.User;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommentMapper {
    public static Comment toModel(CommentPostDto dto, User owner, Event event) {
        return Comment.builder()
                .text(dto.getText())
                .owner(owner)
                .event(event)
                .date(LocalDateTime.now())
                .build();
    }

    public static CommentResponseDto toResponseDto(Comment model, UserShortDto ownerDto, EventShortDto eventDto) {
        return CommentResponseDto.builder()
                .id(model.getId())
                .text(model.getText())
                .owner(ownerDto)
                .event(eventDto)
                .date(DateTimeMapper.toString(model.getDate()))
                .build();
    }
}
