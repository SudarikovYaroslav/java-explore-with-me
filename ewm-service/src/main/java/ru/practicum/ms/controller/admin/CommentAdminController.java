package ru.practicum.ms.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ms.service.CommentService;

@RestController
@AllArgsConstructor
@RequestMapping("/comment/admin")
public class CommentAdminController {

    public static final boolean ADMIN = true;

    private final CommentService commentService;

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteComment(commentId, null, ADMIN);
    }
}
