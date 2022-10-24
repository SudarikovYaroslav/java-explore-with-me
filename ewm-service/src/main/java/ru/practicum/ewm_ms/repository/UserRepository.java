package ru.practicum.ewm_ms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm_ms.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from users as u where u.id in ?1")
    Page<User> findAll(String ids, Pageable pageable);

    @Query("select u from users as u where u.id in ?1")
    List<User> findAll(String ids);
}
