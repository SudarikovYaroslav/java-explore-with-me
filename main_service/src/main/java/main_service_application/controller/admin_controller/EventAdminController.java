package main_service_application.controller.admin_controller;

import main_service_application.dto.event.EventDetailedDto;
import main_service_application.dto.event.EventPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import main_service_application.service.EventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class EventAdminController {

    private static final String DEFAULT_FROM = "0";
    private static final String DEFAULT_SIZE = "10";

    private final EventService eventService;

    @GetMapping
    public List<EventDetailedDto> findEventsByConditions(@RequestParam Long[] userIds,
                                                         @RequestParam String[] states,
                                                         @RequestParam Integer[] categories,
                                                         @RequestParam String rangeStart,
                                                         @RequestParam String rangeEnd,
                                                         @RequestParam(defaultValue = DEFAULT_FROM) Integer from,
                                                         @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {
        return eventService.findEventsByConditions(userIds, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PutMapping("/{eventId}")
    public EventDetailedDto editEvent(@PathVariable Long eventId,
                                      @RequestBody EventPostDto dto) {
        return eventService.editEvent(eventId, dto);
    }

    @PatchMapping("/{eventId}/publish")
    public EventDetailedDto publishEvent(@PathVariable Long eventId) {
        return eventService.publishEvent(eventId);
    }

    @PatchMapping("/{eventId}/reject")
    public EventDetailedDto rejectEvent(@PathVariable Long eventId) {
        return eventService.rejectEvent(eventId);
    }
}
