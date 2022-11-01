package ru.paracticum.ewm_ss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.paracticum.ewm_ss.model.App;

import java.util.Optional;

public interface AppRepository extends JpaRepository<App, Long> {
    Optional<App> findByName(String appName);

    boolean existsByName(String appName);
}
