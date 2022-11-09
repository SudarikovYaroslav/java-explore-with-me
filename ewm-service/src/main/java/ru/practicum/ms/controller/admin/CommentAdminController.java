package ru.practicum.ms.controller.admin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ms.service.CommentService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/comment/admin")
public class CommentAdminController {

    public static final String USER_ID_HEADER = "X-User-Id";

    private final CommentService commentService;

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId,
                                  @RequestHeader(USER_ID_HEADER) Long userId) {
        log.info("Удаление комментария id:{} администратором id:{}", commentId, userId);
        commentService.deleteComment(commentId);
    }
}
