package main_service_application.service;

import main_service_application.dto.compilation.CompilationPostDto;
import main_service_application.dto.compilation.CompilationResponseDto;

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
