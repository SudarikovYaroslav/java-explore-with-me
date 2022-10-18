package statistics_service_application.mapper;

import statistics_service_application.dto.StatisticsDto;
import statistics_service_application.model.Statistics;

public class StatisticsMapper {
    private StatisticsMapper() {}

    public static StatisticsDto toDto(Statistics model) {
        return new StatisticsDto(model.getViews());
    }
}
