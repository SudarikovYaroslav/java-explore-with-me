package ru.practicum.ms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ms.client.EventClient;
import ru.practicum.ms.client.dto.UtilDto;
import ru.practicum.ms.dto.compilation.CompilationPostDto;
import ru.practicum.ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ms.dto.event.EventShortDto;
import ru.practicum.ms.exception.NotFoundException;
import ru.practicum.ms.handler.mappers.CompilationMapper;
import ru.practicum.ms.handler.mappers.EventMapper;
import ru.practicum.ms.model.CompEvent;
import ru.practicum.ms.model.Compilation;
import ru.practicum.ms.model.Event;
import ru.practicum.ms.model.ParticipationState;
import ru.practicum.ms.repository.CompEventsRepository;
import ru.practicum.ms.repository.CompilationRepository;
import ru.practicum.ms.repository.EventRepository;
import ru.practicum.ms.repository.ParticipationRepository;
import ru.practicum.ms.service.CompilationService;
import ru.practicum.ms.util.Util;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ms.util.Util.*;

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

        List<EventShortDto> eventDtos = getEventShortDtoList(events);
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
        return getEventShortDtoList(compilation.getEvents());
    }

    private List<EventShortDto> getEventShortDtoList(List<Event> events) {
        List<Long> eventIds = getEventIdsList(events);
        List<UtilDto> confirmedReqEventIdRelations = participationRepo
                .countParticipationByEventIds(eventIds, ParticipationState.CONFIRMED);
        List<UtilDto> viewsEventIdRelations = client.getViewsByEventIds(eventIds);
        return events.stream()
                .map((Event event) -> EventMapper.toEventShortDto(
                        event,
                        matchIntValueByEventId(confirmedReqEventIdRelations, event.getId()),
                        matchLongValueByEventId(viewsEventIdRelations, event.getId())))
                .collect(Collectors.toList());
    }
}
