package ru.practicum.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ms.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
