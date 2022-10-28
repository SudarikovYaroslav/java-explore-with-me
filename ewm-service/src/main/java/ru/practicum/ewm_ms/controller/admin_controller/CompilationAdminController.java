package ru.practicum.ewm_ms.controller.admin_controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_ms.dto.compilation.CompilationPostDto;
import ru.practicum.ewm_ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ewm_ms.service.CompilationService;

import javax.validation.constraints.Positive;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class CompilationAdminController {

    private final CompilationService compilationService;

    @PostMapping
    public CompilationResponseDto addNewCompilation(@RequestBody CompilationPostDto dto) {
        log.info("add new compilation: {}", dto);
        return compilationService.addNewCompilation(dto);
    }

    @DeleteMapping("/{compId}")
    public void deleteCompilation(@Positive
                                  @PathVariable Long compId) {
        log.info("delete compilation id: {}", compId);
        compilationService.deleteCompilation(compId);
    }

    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(@Positive
                                           @PathVariable Long compId,
                                           @Positive
                                           @PathVariable Long eventId) {
        log.info("delete event id:{} from compilation id:{}", eventId, compId);
        compilationService.deleteEventFromCompilation(compId, eventId);
    }

    @PatchMapping("/{compId}/events/{eventId}")
    public void addEventToCompilation(@Positive
                                      @PathVariable Long compId,
                                      @Positive
                                      @PathVariable Long eventId) {
        log.info("add event id:{} to compilation id:{}", eventId, compId);
        compilationService.addEventToCompilation(compId, eventId);
    }

    @DeleteMapping("/{compId}/pin")
    public void unpinCompilation(@Positive
                                 @PathVariable Long compId) {
        log.info("unpin compilation id: {}", compId);
        compilationService.unpinCompilation(compId);
    }

    @PatchMapping("/{compId}/pin")
    public void pinCompilation(@Positive
                               @PathVariable Long compId) {
        log.info("pin compilation id: {}", compId);
        compilationService.pinCompilation(compId);
    }
}
