package ru.practicum.ms.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ms.dto.comment.CommentPatchDto;
import ru.practicum.ms.dto.comment.CommentPostDto;
import ru.practicum.ms.dto.comment.CommentResponseDto;
import ru.practicum.ms.service.CommentService;
import ru.practicum.ms.util.PatchValidMarker;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/comment")
public class CommentPrivateController {

    public static final String USER_ID_HEADER = "X-User-Id";

    private final CommentService commentService;

    @PostMapping
    public CommentResponseDto postComment(@Valid
                                          @RequestBody CommentPostDto dto,
                                          @RequestHeader(USER_ID_HEADER) Long userId) {
        log.info("Добавление нового комментария:{} пользователем id:{}", dto, userId);
        return commentService.postComment(dto, userId);
    }

    @PatchMapping
    public CommentResponseDto patchComment(@Validated({PatchValidMarker.class})
                                           @RequestBody CommentPatchDto dto,
                                           @RequestHeader(USER_ID_HEADER) Long userId) {
        log.info("Обновление комментария id:{}, {} пользователем id:{}", dto.getId(), dto, userId);
        return commentService.patchComment(dto, userId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId,
                              @RequestHeader(USER_ID_HEADER) Long userId) {
        log.info("Удаление комментария id:{} пользователем id:{}", commentId, userId);
        commentService.deleteComment(commentId, userId);
    }
}
