package ru.practicum.ewm_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm_ms.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
