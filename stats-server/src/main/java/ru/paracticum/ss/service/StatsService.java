package ru.paracticum.ss.service;

import ru.paracticum.ss.dto.HitPostDto;
import ru.paracticum.ss.dto.HitResponseDto;
import ru.paracticum.ss.model.HitSearchParams;

import java.util.List;

public interface StatsService {
    void postHit(HitPostDto dto);

    List<HitResponseDto> getHits(HitSearchParams params);

    Long getViewsByEventId(Long eventId);
}
