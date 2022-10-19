package ru.paracticum.ewm_ss.service;

import ru.paracticum.ewm_ss.dto.HitDto;
import ru.paracticum.ewm_ss.dto.StatisticsDto;

import java.util.List;

public interface StatisticsService {
    void postHit(HitDto dto);

    List<StatisticsDto> getStatistics(String start, String end, String[] uris, Boolean unique);
}
