package statistics_service_application.service;

import statistics_service_application.dto.HitDto;
import statistics_service_application.dto.StatisticsDto;

import java.util.List;

public interface StatisticsService {
    void postHit(HitDto dto);

    List<StatisticsDto> getStatistics(String start, String end, String[] uris, Boolean unique);
}
