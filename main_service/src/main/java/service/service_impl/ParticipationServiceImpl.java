package service.service_impl;

import dto.ParticipationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.ParticipationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {

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
