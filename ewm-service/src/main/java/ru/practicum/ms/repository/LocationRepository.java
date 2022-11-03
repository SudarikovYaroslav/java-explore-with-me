package ru.practicum.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ms.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
