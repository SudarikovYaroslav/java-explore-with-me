package ru.paracticum.ss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.paracticum.ss.model.App;

import java.util.Optional;

public interface AppRepository extends JpaRepository<App, Long> {
    @Query("select ap from apps as ap where ap.name like ?1")
    Optional<App> findByName(String appName);
}
