package statistics_service_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import statistics_service_application.model.Hit;

public interface HitRepository extends JpaRepository<Hit, Long> {
}
