package main_service_application.controller.public_controller;

import main_service_application.dto.event.EventDetailedDto;
import main_service_application.dto.event.EventShortDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import main_service_application.service.EventService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventPublicController {

    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam String text,
                                         @RequestParam Integer[] categories,
                                         @RequestParam Boolean paid,
                                         @RequestParam String rangeStart,
                                         @RequestParam String rangeEnd,
                                         @RequestParam Boolean onlyAvailable,
                                         @RequestParam String sort,
                                         @RequestParam Integer from,
                                         @RequestParam Integer size) {
        return eventService.getEvents(
                text,
                categories,
                paid,
                rangeStart,
                rangeEnd,
                onlyAvailable,
                sort,
                from,
                size
        ) ;
    }

    @GetMapping("/{id}")
    public EventDetailedDto findEventById(@PathVariable Long id)  {
        return eventService.findEventById(id);
    }
}
