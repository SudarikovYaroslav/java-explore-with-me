package ru.paracticum.ewm_ss.mapper;

import ru.paracticum.ewm_ss.dto.StatisticsDto;
import ru.paracticum.ewm_ss.model.Statistics;

public class StatisticsMapper {
    private StatisticsMapper() {}

    public static StatisticsDto toDto(Statistics model) {
        return StatisticsDto.builder()
                .app(model.getApp())
                .uri(model.getUri())
                .hits(model.getHits())
                .build();
    }
}