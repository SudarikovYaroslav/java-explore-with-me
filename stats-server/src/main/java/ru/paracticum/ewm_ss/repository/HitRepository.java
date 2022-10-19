package ru.paracticum.ewm_ss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.paracticum.ewm_ss.model.Hit;

public interface HitRepository extends JpaRepository<Hit, Long> {
}
