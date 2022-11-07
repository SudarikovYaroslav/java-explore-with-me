package ru.practicum.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ms.model.Comment;

public interface CommentRepository extends JpaRepository <Comment, Long> {
}
