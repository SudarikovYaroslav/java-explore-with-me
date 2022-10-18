package statistics_service_application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import statistics_service_application.repository.ViewRepository;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final ViewRepository viewRepository;
}
