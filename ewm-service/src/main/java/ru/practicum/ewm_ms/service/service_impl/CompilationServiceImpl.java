package ru.practicum.ewm_ms.service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.ewm_ms.dto.compilation.CompilationPostDto;
import ru.practicum.ewm_ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.mappers.CompilationMapper;
import ru.practicum.ewm_ms.model.CompEvent;
import ru.practicum.ewm_ms.model.Compilation;
import ru.practicum.ewm_ms.repository.CompEventsRepository;
import ru.practicum.ewm_ms.repository.CompilationRepository;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.service.CompilationService;
import ru.practicum.ewm_ms.util.MainServiceUtil;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm_ms.util.MainServiceUtil.*;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepo;
    private final CompEventsRepository compEventsRepo;
    private final EventRepository eventRepo;

    @Override
    public List<CompilationResponseDto> findAll(Boolean pinned, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Compilation> compilations = compilationRepo.findAllByPinned(pinned, pageable);
        return compilations
                .stream()
                .map(CompilationMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationResponseDto findById(Long compId) {
        Compilation compilation = compilationRepo.findById(compId).orElse(null);
        if (compilation == null) {
            throw new NotFoundException(MainServiceUtil.getCompilationNotFoundMessage(compId));
        }
        return CompilationMapper.toResponseDto(compilation);
    }

    @Override
    public CompilationResponseDto addNewCompilation(CompilationPostDto dto) {
        Compilation compilation = CompilationMapper.toModel(dto, eventRepo);
        compilation = compilationRepo.save(compilation);
        return CompilationMapper.toResponseDto(compilation);
    }

    @Override
    public void deleteCompilation(Long compId) {
        compilationRepo.deleteById(compId);
    }

    @Override
    public void deleteEventFromCompilation(Long compId, Long eventId) {
        cheIfCompilationExists(compId, compilationRepo);
        checkIfEventExists(eventId, eventRepo);
        compEventsRepo.deleteByCompilationIdAndEventId(compId, eventId);
    }

    @Override
    public void addEventToCompilation(Long compId, Long eventId) {
        cheIfCompilationExists(compId, compilationRepo);
        checkIfEventExists(eventId, eventRepo);
        CompEvent compEvent = new CompEvent(compId, eventId);
        compEventsRepo.save(compEvent);
    }

    @Override
    public void unpinCompilation(Long compId) {
        Compilation compilation = cheIfCompilationExists(compId, compilationRepo);
        if (compilation.getPinned()) {
            compilation.setPinned(false);
            compilationRepo.save(compilation);
        }
    }

    @Override
    public void pinCompilation(Long compId) {
        Compilation compilation = cheIfCompilationExists(compId, compilationRepo);
        if (!compilation.getPinned()) {
            compilation.setPinned(true);
            compilationRepo.save(compilation);
        }
    }
}
