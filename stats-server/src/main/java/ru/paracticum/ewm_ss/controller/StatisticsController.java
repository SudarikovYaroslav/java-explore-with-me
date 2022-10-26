package ru.paracticum.ewm_ss.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.paracticum.ewm_ss.dto.HitPostDto;
import ru.paracticum.ewm_ss.dto.HitResponseDto;
import ru.paracticum.ewm_ss.model.HitSearchParams;
import ru.paracticum.ewm_ss.service.StatsService;

import javax.validation.constraints.NotBlank;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final StatsService statsService;

    @PostMapping("/hit")
    public void postHit(@RequestBody HitPostDto dto) {
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
                URLEncoder.encode(start, StandardCharsets.UTF_8),
                URLEncoder.encode(end, StandardCharsets.UTF_8),
                uris,
                unique);
        return statsService.getHits(params);
    }
}
