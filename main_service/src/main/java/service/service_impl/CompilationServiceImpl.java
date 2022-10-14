package service.service_impl;

import dto.compilation.CompilationPostDto;
import dto.compilation.CompilationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.CompilationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

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
