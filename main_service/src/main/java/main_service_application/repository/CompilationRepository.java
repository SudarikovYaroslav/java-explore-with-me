package main_service_application.repository;

import main_service_application.model.Compilation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
}
