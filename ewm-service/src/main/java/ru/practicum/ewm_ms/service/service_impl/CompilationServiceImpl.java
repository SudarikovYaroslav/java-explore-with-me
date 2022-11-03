package ru.practicum.ewm_ms.service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm_ms.client.EventClient;
import ru.practicum.ewm_ms.dto.compilation.CompilationPostDto;
import ru.practicum.ewm_ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.mappers.CompilationMapper;
import ru.practicum.ewm_ms.mappers.EventMapper;
import ru.practicum.ewm_ms.model.CompEvent;
import ru.practicum.ewm_ms.model.Compilation;
import ru.practicum.ewm_ms.model.Event;
import ru.practicum.ewm_ms.model.ParticipationState;
import ru.practicum.ewm_ms.repository.CompEventsRepository;
import ru.practicum.ewm_ms.repository.CompilationRepository;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.repository.ParticipationRepository;
import ru.practicum.ewm_ms.service.CompilationService;
import ru.practicum.ewm_ms.util.Util;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompilationServiceImpl implements CompilationService {

    private final ParticipationRepository participationRepo;
    private final CompilationRepository compilationRepo;
    private final CompEventsRepository compEventsRepo;
    private final EventRepository eventRepo;
    private final EventClient client;

    @Override
    public List<CompilationResponseDto> findAll(Boolean pinned, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Compilation> compilations;
        if (pinned != null) {
            compilations = compilationRepo.findAllByPinned(pinned, pageable);
        } else {
            compilations = compilationRepo.findAll(pageable).toList();
        }

        return compilations
                .stream()
                .map((Compilation compilation) -> CompilationMapper.toResponseDto(
                        compilation, getEventShortDtos(compilation)
                ))
                .collect(Collectors.toList());
    }

    @Override
    public CompilationResponseDto findById(Long compId) {
        Compilation compilation = compilationRepo.findById(compId)
                .orElseThrow(() -> new NotFoundException(Util.getCompilationNotFoundMessage(compId)));

        List<EventShortDto> eventDtos = getEventShortDtos(compilation);
        return CompilationMapper.toResponseDto(compilation, eventDtos);
    }

    @Override
    @Transactional
    public CompilationResponseDto addNewCompilation(CompilationPostDto dto) {
        List<Event> events = eventRepo.findAll(dto.getEvents());
        Compilation compilation = CompilationMapper.toModel(dto, events);
        compilation = compilationRepo.save(compilation);

        List<EventShortDto> eventDtos = events.stream()
                .map((Event event) -> EventMapper.toEventShortDto(event,
                        participationRepo.getConfirmedRequests(event.getId(), ParticipationState.CONFIRMED),
                        client.getViewsByEventId(event.getId()).getBody()))
                .collect(Collectors.toList());
        return CompilationMapper.toResponseDto(compilation, eventDtos);
    }

    @Override
    @Transactional
    public void deleteCompilation(Long compId) {
        compilationRepo.deleteById(compId);
    }

    @Override
    @Transactional
    public void deleteEventFromCompilation(Long compId, Long eventId) {
        compilationRepo
                .findById(compId).orElseThrow(() -> new NotFoundException(Util.getCompilationNotFoundMessage(compId)));
        eventRepo.findById(eventId).orElseThrow(() -> new NotFoundException(Util.getEventNotFoundMessage(eventId)));
        CompEvent compEvent = new CompEvent(compId, eventId);
        compEventsRepo.deleteByCompilationIdAndEventId(compId, eventId);
    }

    @Override
    @Transactional
    public void addEventToCompilation(Long compId, Long eventId) {
        compilationRepo
                .findById(compId).orElseThrow(() -> new NotFoundException(Util.getCompilationNotFoundMessage(compId)));
        eventRepo.findById(eventId).orElseThrow(() -> new NotFoundException(Util.getEventNotFoundMessage(eventId)));
        CompEvent compEvent = new CompEvent(compId, eventId);
        compEventsRepo.save(compEvent);
    }

    @Override
    @Transactional
    public void unpinCompilation(Long compId) {
        Compilation compilation = compilationRepo
                .findById(compId).orElseThrow(() -> new NotFoundException(Util.getCompilationNotFoundMessage(compId)));
        compilation.setPinned(false);
    }

    @Override
    @Transactional
    public void pinCompilation(Long compId) {
        Compilation compilation = compilationRepo
                .findById(compId).orElseThrow(() -> new NotFoundException(Util.getCompilationNotFoundMessage(compId)));
        compilation.setPinned(true);
    }

    private List<EventShortDto> getEventShortDtos(Compilation compilation) {
        return compilation.getEvents().stream()
                .map((Event event) -> EventMapper.toEventShortDto(event,
                        participationRepo.getConfirmedRequests(event.getId(), ParticipationState.CONFIRMED),
                        client.getViewsByEventId(event.getId()).getBody()))
                .collect(Collectors.toList());
    }
//    private static List<Event> replaceIdWithEvents(List<Long> ids, EventRepository repo) {
//        return repo.findAll(ids);
//    }
}
