package ru.practicum.ewm_ms.controller.public_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ewm_ms.service.CompilationService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

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
                                                @RequestParam(defaultValue = DEFAULT_FROM) Integer from,
                                                @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {
        return compilationService.findAll(pinned, from, size) ;
    }

    @GetMapping("/{compId}")
    public CompilationResponseDto findById(@Positive
                                           @PathVariable Long compId) {
        return compilationService.findById(compId);
    }
}