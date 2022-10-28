package ru.practicum.ewm_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm_ms.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
