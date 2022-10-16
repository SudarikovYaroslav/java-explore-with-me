package main_service_application.controller.public_controller;

import main_service_application.dto.compilation.CompilationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import main_service_application.service.CompilationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compilations")
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
