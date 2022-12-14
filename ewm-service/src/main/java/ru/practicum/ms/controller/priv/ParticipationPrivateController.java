package ru.practicum.ms.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ms.dto.ParticipationDto;
import ru.practicum.ms.service.ParticipationService;

import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/requests")
public class ParticipationPrivateController {

    private final ParticipationService participationService;

    @GetMapping
    public List<ParticipationDto> getInfoAboutAllParticipation(@Positive
                                                               @PathVariable Long userId) {
        log.info("get info about all participation user id:{}", userId);
        return participationService.getInfoAboutAllParticipation(userId);
    }

    @PostMapping
    public ParticipationDto addParticipationQuery(@Positive
                                                  @PathVariable Long userId,
                                                  @Positive
                                                  @RequestParam Long eventId) {
        log.info("add participation query user id:{}, event id:{}", userId, eventId);
        return participationService.addParticipationQuery(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationDto cancelParticipation(@PathVariable Long userId,
                                                @PathVariable Long requestId) {
        log.info("cancel participation user id:{}, request id:{}", userId, requestId);
        return participationService.cancelParticipation(userId, requestId);
    }
}