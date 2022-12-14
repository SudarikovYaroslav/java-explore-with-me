package ru.paracticum.ss.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.paracticum.ss.dto.BoxDto;
import ru.paracticum.ss.dto.HitPostDto;
import ru.paracticum.ss.dto.HitResponseDto;
import ru.paracticum.ss.model.HitSearchParams;
import ru.paracticum.ss.service.StatsService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final StatsService statsService;

    @PostMapping("/hit")
    public void postHit(@Valid
                        @RequestBody HitPostDto dto) {
        log.info("Сохраняется запрос: {}", dto);
        statsService.postHit(dto);
    }

    @GetMapping("/stats")
    public List<HitResponseDto> getStatistics(@RequestParam @NotBlank String start,
                                              @RequestParam @NotBlank String end,
                                              @RequestParam List<String> uris,
                                              @RequestParam Boolean unique) {
        log.info("Получение статистики с параметрами start:{}, end:{}, uris:{}, unique:{}", start, end, uris, unique);
        HitSearchParams params = new HitSearchParams(
                start,
                end,
                uris,
                unique);
        return statsService.getHits(params);
    }

    @GetMapping("/views/{eventId}")
    public Long getViewsByEventId(@Positive
                                  @PathVariable Long eventId) {
        log.info("Получение количества просмотров для события id: {}", eventId);
        return statsService.getViewsByEventId(eventId);
    }

    @GetMapping("/views")
    public BoxDto getViewsByEventIds(@RequestParam List<String> ids) {
        log.info("Получение количества просмотров для событий: {}", ids.toString());
        return statsService.getViewsByEventIds(ids);
    }
}