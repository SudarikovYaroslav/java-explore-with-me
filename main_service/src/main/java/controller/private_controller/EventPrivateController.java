package controller.private_controller;

import dto.ParticipationDto;
import dto.event.EventDetailedDto;
import dto.event.EventPatchDto;
import dto.event.EventPostDto;
import dto.event.EventShortDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.EventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class EventPrivateController {

    private static final String DEFAULT_FROM = "0";
    private static final String DEFAULT_SIZE = "10";

    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> findEventsByUserId(@PathVariable Long userId,
                                                  @RequestParam(defaultValue = DEFAULT_FROM) Integer from,
                                                  @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {
        return eventService.findEventsByUserId(userId, from, size);
    }

    @PatchMapping
    public EventDetailedDto patchEvent(@PathVariable Long userId,
                                       @RequestBody EventPatchDto dto) {
        return eventService.patchEvent(userId, dto);
    }

    @PostMapping
    public EventDetailedDto postEvent(@PathVariable Long userId,
                                      @RequestBody EventPostDto dto) {
        return eventService.postEvent(userId, dto);
    }

    @GetMapping("/{eventId}")
    public EventDetailedDto findEventByIdAndOwnerId(@PathVariable Long userId,
                                                    @PathVariable Long eventId) {
        return eventService.findEventByIdAndOwnerId(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventDetailedDto canselEventByIdAndOwnerId(@PathVariable Long userId,
                                                      @PathVariable Long eventId) {
        return eventService.cancelEventByIdAndOwnerId(userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    public ParticipationDto getInfoAboutEventParticipation(@PathVariable Long userId,
                                                           @PathVariable Long eventId) {
        return eventService.getInfoAboutEventParticipation(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public ParticipationDto confirmParticipation(@PathVariable Long userId,
                                                 @PathVariable Long eventId,
                                                 @PathVariable Long reqId) {
        return eventService.confirmParticipation(userId, eventId, reqId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public ParticipationDto rejectParticipation(@PathVariable Long userId,
                                                @PathVariable Long eventId,
                                                @PathVariable Long reqId) {
        return eventService.rejectParticipation(userId, eventId, reqId);
    }
}
