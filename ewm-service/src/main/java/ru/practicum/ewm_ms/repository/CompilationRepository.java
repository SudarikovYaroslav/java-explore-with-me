package ru.practicum.ewm_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm_ms.model.Compilation;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
}
