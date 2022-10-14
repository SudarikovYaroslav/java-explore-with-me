package controller.public_controller;

import dto.event.EventDetailedDto;
import dto.event.EventShortDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.EventService;

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
