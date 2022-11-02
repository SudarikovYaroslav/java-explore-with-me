package ru.paracticum.ewm_ss.service;

import ru.paracticum.ewm_ss.dto.HitPostDto;
import ru.paracticum.ewm_ss.dto.HitResponseDto;
import ru.paracticum.ewm_ss.model.HitSearchParams;

import java.util.List;

public interface StatsService {
    void postHit(HitPostDto dto);

    List<HitResponseDto> getHits(HitSearchParams params);

    Long getViewsByEventId(Long eventId);
}
