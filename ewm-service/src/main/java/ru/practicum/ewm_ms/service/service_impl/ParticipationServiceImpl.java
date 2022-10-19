package ru.practicum.ewm_ms.service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm_ms.dto.ParticipationDto;
import ru.practicum.ewm_ms.repository.ParticipationRepository;
import ru.practicum.ewm_ms.service.ParticipationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;

    //TODO добавить маппер, репозиторий и реализовать логику
    @Override
    public List<ParticipationDto> getInfoAboutAllParticipation(Long userId) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public ParticipationDto addParticipationQuery(Long userId, Long eventId) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public ParticipationDto cancelParticipation(Long userId, Long requestId) {
        return null;
    }
}
