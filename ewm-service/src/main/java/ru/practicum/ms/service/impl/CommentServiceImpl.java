package ru.practicum.ms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ms.dto.comment.CommentPatchDto;
import ru.practicum.ms.dto.comment.CommentPostDto;
import ru.practicum.ms.dto.comment.CommentResponseDto;
import ru.practicum.ms.repository.CommentRepository;
import ru.practicum.ms.repository.EventRepository;
import ru.practicum.ms.repository.UserRepository;
import ru.practicum.ms.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepo;
    private final EventRepository eventRepo;
    private final UserRepository userRepo;

    @Override
    public CommentResponseDto postComment(CommentPostDto comment) {
        return null;
    }

    @Override
    public CommentResponseDto patchComment(CommentPatchDto comment, Long userId, boolean admin) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId, Long userId, boolean admin) {

    }

    @Override
    public CommentResponseDto findCommentById(Long commentId) {
        return null;
    }

    @Override
    public List<CommentResponseDto> findCommentsByEventId(Long eventId) {
        return null;
    }
}
