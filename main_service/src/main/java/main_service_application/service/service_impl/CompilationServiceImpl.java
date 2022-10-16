package main_service_application.service.service_impl;

import main_service_application.dto.compilation.CompilationPostDto;
import main_service_application.dto.compilation.CompilationResponseDto;
import lombok.RequiredArgsConstructor;
import main_service_application.repository.CompilationRepository;
import main_service_application.service.CompilationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;

    //TODO реализовать логику, добавить маппер и репозиторий
    @Override
    public List<CompilationResponseDto> findAll(Boolean pinned, Integer from, Integer size) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public CompilationResponseDto findById(Long compId) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public CompilationResponseDto addNewCompilation(CompilationPostDto dto) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public void deleteCompilation(Long compId) {}

    //TODO реализовать логику
    @Override
    public void deleteEventFromCompilation(Long compId, Long eventId) {}

    //TODO реализовать логику
    @Override
    public void addEventToCompilation(Long compId, Long eventId) {}

    //TODO реализовать логику
    @Override
    public void unpinCompilation(Long compId) {
    }

    //TODO реализовать логику
    @Override
    public void pinCompilation(Long compId) {
    }
}
