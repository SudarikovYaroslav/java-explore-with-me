package statistics_service_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import statistics_service_application.model.View;

public interface ViewRepository extends JpaRepository<View, Long> {
}
