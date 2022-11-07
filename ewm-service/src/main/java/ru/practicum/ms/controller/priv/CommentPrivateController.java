package ru.practicum.ms.controller.priv;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ms.dto.comment.CommentPatchDto;
import ru.practicum.ms.dto.comment.CommentPostDto;
import ru.practicum.ms.dto.comment.CommentResponseDto;
import ru.practicum.ms.service.CommentService;
import ru.practicum.ms.util.PatchValidMarker;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/comment")
public class CommentPrivateController {

    public static final String USER_ID_HEADER = "X-User-Id";
    public static final boolean ADMIN = false;

    private final CommentService commentService;

    @PostMapping
    public CommentResponseDto postComment(@Valid
                                          @RequestBody CommentPostDto dto) {
        return commentService.postComment(dto);
    }

    @PatchMapping
    public CommentResponseDto patchComment(@Validated({PatchValidMarker.class})
                                           @RequestBody CommentPatchDto dto,
                                           @RequestHeader(USER_ID_HEADER) Long userId) {
        return commentService.patchComment(dto, userId, ADMIN);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId,
                              @RequestHeader(USER_ID_HEADER) Long userId) {
        commentService.deleteComment(commentId, userId, ADMIN);
    }
}
