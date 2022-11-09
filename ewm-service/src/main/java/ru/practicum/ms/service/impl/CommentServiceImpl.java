package ru.practicum.ms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ms.client.EventClient;
import ru.practicum.ms.client.dto.UtilDto;
import ru.practicum.ms.dto.comment.CommentPatchDto;
import ru.practicum.ms.dto.comment.CommentPostDto;
import ru.practicum.ms.dto.comment.CommentResponseDto;
import ru.practicum.ms.dto.event.EventShortDto;
import ru.practicum.ms.dto.user.UserShortDto;
import ru.practicum.ms.exception.ForbiddenException;
import ru.practicum.ms.exception.NotFoundException;
import ru.practicum.ms.handler.mappers.CommentMapper;
import ru.practicum.ms.handler.mappers.EventMapper;
import ru.practicum.ms.handler.mappers.UserMapper;
import ru.practicum.ms.model.Comment;
import ru.practicum.ms.model.Event;
import ru.practicum.ms.model.ParticipationState;
import ru.practicum.ms.model.User;
import ru.practicum.ms.repository.CommentRepository;
import ru.practicum.ms.repository.EventRepository;
import ru.practicum.ms.repository.ParticipationRepository;
import ru.practicum.ms.repository.UserRepository;
import ru.practicum.ms.service.CommentService;
import ru.practicum.ms.util.Util;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ms.util.Util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final ParticipationRepository participationRepo;
    private final CommentRepository commentRepo;
    private final EventRepository eventRepo;
    private final UserRepository userRepo;
    private final EventClient client;

    @Override
    @Transactional
    public CommentResponseDto postComment(CommentPostDto dto) {
        User owner = userRepo.findById(dto.getOwnerId())
                .orElseThrow(() -> new NotFoundException(Util.getUserNotFoundMessage(dto.getOwnerId())));
        Event event = eventRepo.findById(dto.getEventId())
                .orElseThrow(() -> new NotFoundException(Util.getEventNotFoundMessage(dto.getEventId())));
        Comment comment = CommentMapper.toModel(dto, owner, event);
        comment = commentRepo.save(comment);
        return mapToCommentResponseDto(comment);
    }

    @Override
    @Transactional
    public CommentResponseDto patchComment(CommentPatchDto dto, Long userId) {
        if (!dto.getId().equals(userId)) {
            throw new ForbiddenException("Пользователь id:" + userId
                    + " не является создателем комментария");
        }
        Comment comment = commentRepo.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(Util.getCommentNotFoundMessage(dto.getId())));
        updateComment(comment, dto);
        return mapToCommentResponseDto(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, Long userId, boolean admin) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new NotFoundException(Util.getCommentNotFoundMessage(commentId)));
        Long ownerId = comment.getOwner().getId();
        if (!ownerId.equals(userId)) {
            if (admin) {
                commentRepo.deleteById(commentId);
                return;
            }
            throw new ForbiddenException("Пользователь id:" + userId
                    + " не является создателем комментария или администратором");
        }
        commentRepo.deleteById(commentId);
    }

    @Override
    public CommentResponseDto findCommentById(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new NotFoundException(Util.getCommentNotFoundMessage(commentId)));
        return mapToCommentResponseDto(comment);
    }

    @Override
    public List<CommentResponseDto> findCommentsByEventId(Long eventId) {
        List<Comment> comments = commentRepo.findAllByEventId(eventId);
        return mapToCommentResponseDtoList(comments);
    }

    private void updateComment(Comment comment, CommentPatchDto dto) {
        if (dto.getText() != null && !dto.getText().isBlank()) {
            comment.setText(dto.getText());
        }
    }

    private CommentResponseDto mapToCommentResponseDto(Comment comment) {
        UserShortDto ownerDto = UserMapper.toUserShortDto(comment.getOwner());

        Event event = comment.getEvent();
        Integer confirmedRequests = participationRepo.getConfirmedRequests(event.getId(), ParticipationState.CONFIRMED);
        Long views = client.getViewsByEventId(event.getId()).getBody();
        EventShortDto eventDto = EventMapper.toEventShortDto(event, confirmedRequests, views);
        return CommentMapper.toResponseDto(comment, ownerDto, eventDto);
    }

    private List<CommentResponseDto> mapToCommentResponseDtoList(List<Comment> comments) {
        List<Event> events = comments.stream().map(Comment::getEvent).collect(Collectors.toList());
        List<EventShortDto> eventDtos = prepareDataAndGetEventShortDtoList(events);

        return comments.stream().map(comment -> {
            UserShortDto ownerDto = UserMapper.toUserShortDto(comment.getOwner());
            long eventId = comment.getEvent().getId();
            EventShortDto eventDto = eventDtos.stream()
                    .filter(eventShortDto -> eventShortDto.getId().equals(eventId))
                    .findFirst().orElseThrow(() -> new NotFoundException(Util.getEventNotFoundMessage(eventId)));
            return CommentMapper.toResponseDto(comment, ownerDto, eventDto);
        }).collect(Collectors.toList());
    }

    private List<EventShortDto> prepareDataAndGetEventShortDtoList(List<Event> events) {
        List<Long> eventIds = getEventIdsList(events);
        List<UtilDto> confirmedReqEventIdRelations = participationRepo
                .countParticipationByEventIds(eventIds, ParticipationState.CONFIRMED);
        List<UtilDto> viewsEventIdRelations = client.getViewsByEventIds(eventIds);
        return events.stream()
                .map((Event event) -> EventMapper.toEventShortDto(
                        event,
                        matchIntValueByEventId(confirmedReqEventIdRelations, event.getId()),
                        matchLongValueByEventId(viewsEventIdRelations, event.getId())))
                .collect(Collectors.toList());
    }
}
