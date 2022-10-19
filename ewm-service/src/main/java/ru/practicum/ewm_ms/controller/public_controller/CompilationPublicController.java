package ru.practicum.ewm_ms.controller.public_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ewm_ms.service.CompilationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/compilations")
public class CompilationPublicController {

    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationResponseDto> findAll(@RequestParam Boolean pinned,
                                                @RequestParam Integer from,
                                                @RequestParam Integer size) {
        return compilationService.findAll(pinned, from, size) ;
    }

    @GetMapping("/{compId}")
    public CompilationResponseDto findById(@PathVariable Long compId) {
        return compilationService.findById(compId);
    }
}
