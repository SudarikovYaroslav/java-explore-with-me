package ru.practicum.ms.service;

import ru.practicum.ms.dto.comment.CommentPatchDto;
import ru.practicum.ms.dto.comment.CommentPostDto;
import ru.practicum.ms.dto.comment.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto postComment(CommentPostDto comment);

    CommentResponseDto patchComment(CommentPatchDto comment, Long userId);

    void deleteComment(Long commentId, Long userId, boolean admin);

    CommentResponseDto findCommentById(Long commentId);

    List<CommentResponseDto> findCommentsByEventId(Long eventId);
}
