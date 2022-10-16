package main_service_application.service.service_impl;

import main_service_application.dto.ParticipationDto;
import lombok.RequiredArgsConstructor;
import main_service_application.repository.ParticipationRepository;
import org.springframework.stereotype.Service;
import main_service_application.service.ParticipationService;

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
