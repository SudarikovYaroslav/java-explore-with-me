package ru.practicum.ewm_ms.controller.public_controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_ms.dto.event.EventDetailedDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;
import ru.practicum.ewm_ms.model.EventSearchParams;
import ru.practicum.ewm_ms.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventPublicController {

    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam String text,
                                         @RequestParam List<Long> categories,
                                         @RequestParam Boolean paid,
                                         @RequestParam String rangeStart,
                                         @RequestParam String rangeEnd,
                                         @RequestParam Boolean onlyAvailable,
                                         @RequestParam String sort,
                                         @RequestParam Integer from,
                                         @RequestParam Integer size,
                                         HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        String endpoint = request.getRequestURI();
        log.info("client ip: {}", clientIp);
        log.info("endpoint path: {}", endpoint);
        EventSearchParams criteria = new EventSearchParams(
                text,
                categories,
                paid,
                rangeStart,
                rangeEnd,
                onlyAvailable,
                sort,
                from,
                size
        );
        return eventService.getEvents(criteria, clientIp, endpoint) ;
    }

    @GetMapping("/{id}")
    public EventDetailedDto findEventById(@PathVariable Long id,
                                          HttpServletRequest request)  {
        String clientIp = request.getRemoteAddr();
        String endpoint = request.getRequestURI();
        log.info("client ip: {}", clientIp);
        log.info("endpoint path: {}", endpoint);
        return eventService.findEventById(id, clientIp, endpoint);
    }
}
