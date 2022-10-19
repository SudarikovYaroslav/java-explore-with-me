package ru.paracticum.ewm_ss.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.paracticum.ewm_ss.dto.HitDto;
import ru.paracticum.ewm_ss.dto.StatisticsDto;
import ru.paracticum.ewm_ss.service.StatisticsService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @PostMapping("/hit")
    public void postHit(@RequestBody HitDto dto) {
        log.info("Сохраняется запрос: {}", dto);
        statisticsService.postHit(dto);
    }

    //TODO разобраться, что метод должен вернуть в качестве ответа. Похоже, что StatisticsDto[], но не понятно,
    // какой в этом смысл - получать сразу всю статистику, если например нужна только по одному конкретному запросу
    // *** НУЖНО ДОБАВИТЬ КОДИРОВАНИЕ ДАТЫ ПРИ ПЕРЕДАЧЕ СТАТИСТИКИ ??? Думаю попробовать Base64
    // !!!!!!!! пока считаю, что start и end закодированы при получении и требую декодирования в сервисе
    @GetMapping("/stats")
    public List<StatisticsDto> getStatistics(@RequestParam String start,
                                             @RequestParam String end,
                                             @RequestParam String[] uris,
                                             @RequestParam Boolean unique) {
        return statisticsService.getStatistics(start, end, uris, unique);
    }
}
