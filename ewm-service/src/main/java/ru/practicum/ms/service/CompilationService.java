package ru.practicum.ms.service;

import ru.practicum.ms.dto.compilation.CompilationPostDto;
import ru.practicum.ms.dto.compilation.CompilationResponseDto;

import java.util.List;

public interface CompilationService {
    List<CompilationResponseDto> findAll(Boolean pinned, Integer from, Integer size);

    CompilationResponseDto findById(Long compId);

    CompilationResponseDto addNewCompilation(CompilationPostDto dto);

    void deleteCompilation(Long compId);

    void deleteEventFromCompilation(Long compId, Long eventId);

    void addEventToCompilation(Long compId, Long eventId);

    void unpinCompilation(Long compId);

    void pinCompilation(Long compId);

}
