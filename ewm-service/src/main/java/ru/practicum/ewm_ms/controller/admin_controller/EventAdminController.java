package ru.practicum.ewm_ms.controller.admin_controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_ms.dto.event.EventDetailedDto;
import ru.practicum.ewm_ms.dto.event.EventPostDto;
import ru.practicum.ewm_ms.model.EventSearchParams;
import ru.practicum.ewm_ms.service.EventService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/events")
public class EventAdminController {

    public static final String DEFAULT_FROM = "0";
    public static final String DEFAULT_SIZE = "10";

    private final EventService eventService;

    @GetMapping
    public List<EventDetailedDto> findEventsByConditions(@RequestParam List<Long> userIds,
                                                         @RequestParam List<String> states,
                                                         @RequestParam List<Long> categories,
                                                         @RequestParam String rangeStart,
                                                         @RequestParam String rangeEnd,
                                                         @PositiveOrZero
                                                         @RequestParam(defaultValue = DEFAULT_FROM) Integer from,
                                                         @Positive
                                                         @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {
        EventSearchParams searchParams = new EventSearchParams(
                userIds,
                states,
                categories,
                rangeStart,
                rangeEnd,
                from,
                size
        );
        log.info("find event by conditions {}", searchParams);
        return eventService.findEventsByConditions(searchParams);
    }

    @PutMapping("/{eventId}")
    public EventDetailedDto editEvent(@Positive
                                      @PathVariable Long eventId,
                                      @RequestBody EventPostDto dto) {
        log.info("edit event id:{}, {}", eventId, dto);
        return eventService.editEvent(eventId, dto);
    }

    @PatchMapping("/{eventId}/publish")
    public EventDetailedDto publishEvent(@Positive
                                         @PathVariable Long eventId) {
        log.info("publish event id: {}", eventId);
        return eventService.publishEvent(eventId);
    }

    @PatchMapping("/{eventId}/reject")
    public EventDetailedDto rejectEvent(@Positive
                                        @PathVariable Long eventId) {
        log.info("reject event id: {}", eventId);
        return eventService.rejectEvent(eventId);
    }
}
