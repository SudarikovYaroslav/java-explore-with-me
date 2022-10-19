package ru.practicum.ewm_ms.controller.private_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_ms.dto.ParticipationDto;
import ru.practicum.ewm_ms.service.ParticipationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/requests")
public class ParticipationPrivateController {

    private final ParticipationService participationService;

    @GetMapping
    public List<ParticipationDto> getInfoAboutAllParticipation(@PathVariable Long userId) {
        return participationService.getInfoAboutAllParticipation(userId);
    }

    @PostMapping
    public ParticipationDto addParticipationQuery(@PathVariable Long userId,
                                                  @RequestParam Long eventId) {
        return participationService.addParticipationQuery(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationDto cancelParticipation(@PathVariable Long userId,
                                                @PathVariable Long requestId) {
        return participationService.cancelParticipation(userId, requestId);
    }
}