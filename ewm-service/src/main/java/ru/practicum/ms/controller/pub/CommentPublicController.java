package ru.practicum.ms.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ms.dto.comment.CommentResponseDto;
import ru.practicum.ms.service.CommentService;

import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/comment")
public class CommentPublicController {

    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public CommentResponseDto findCommentById(@Positive
                                              @PathVariable Long commentId) {
        log.info("Просмотр комментария id:{}", commentId);
        return commentService.findCommentById(commentId);
    }

    @GetMapping("/event/{eventId}")
    public List<CommentResponseDto> findCommentsByEventId(@Positive
                                                          @PathVariable Long eventId) {
        log.info("Просмотр комментариев события id:{}", eventId);
        return commentService.findCommentsByEventId(eventId);
    }
}
