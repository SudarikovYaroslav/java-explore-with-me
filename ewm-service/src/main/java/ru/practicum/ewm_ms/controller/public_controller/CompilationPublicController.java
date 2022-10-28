package ru.practicum.ewm_ms.controller.public_controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ewm_ms.service.CompilationService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/compilations")
public class CompilationPublicController {

    public static final String DEFAULT_FROM = "0";
    public static final String DEFAULT_SIZE = "10";

    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationResponseDto> findAll(@NotNull
                                                @RequestParam Boolean pinned,
                                                @PositiveOrZero
                                                @RequestParam(defaultValue = DEFAULT_FROM) Integer from,
                                                @Positive
                                                @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {
        log.info("find all compilations from:{}, size:{}", from, size);
        return compilationService.findAll(pinned, from, size) ;
    }

    @GetMapping("/{compId}")
    public CompilationResponseDto findById(@Positive
                                           @PathVariable Long compId) {
        log.info("find compilation by id:{}", compId);
        return compilationService.findById(compId);
    }
}