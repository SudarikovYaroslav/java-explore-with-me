package ru.practicum.ewm_ms.service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm_ms.dto.ParticipationDto;
import ru.practicum.ewm_ms.exception.ForbiddenException;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.mappers.ParticipationMapper;
import ru.practicum.ewm_ms.model.*;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.repository.ParticipationRepository;
import ru.practicum.ewm_ms.repository.UserRepository;
import ru.practicum.ewm_ms.service.ParticipationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm_ms.util.Util.checkIfEventExists;
import static ru.practicum.ewm_ms.util.Util.checkIfUserExists;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepo;
    private final EventRepository eventRepo;
    private final UserRepository userRepo;

    @Override
    public List<ParticipationDto> getInfoAboutAllParticipation(Long userId) {
        checkIfUserExists(userId, userRepo);
        List<Participation> participations = participationRepo.findAllByRequesterId(userId);
        return participations.stream().map(ParticipationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ParticipationDto addParticipationQuery(Long userId, Long eventId) {
        User user = checkIfUserExists(userId, userRepo);
        Event event = checkIfEventExists(eventId, eventRepo);

        Participation participation = participationRepo
                .findByEventIdAndRequesterId(eventId, userId).orElse(null);
        if (participation != null) {
            return ParticipationMapper.toDto(participation);
        }
        if (event.getInitiator().getId().equals(userId)) {
            throw new ForbiddenException("User id:" + userId
                    + " the user cannot apply to participate in their own event");
        }
        if (!event.getState().equals(PublicationState.PUBLISHED)) {
            throw new ForbiddenException("you cannot take part in an unpublished event");
        }
        if (event.getParticipantLimit() != 0 && event.getConfirmedRequests() >= event.getParticipantLimit()) {
            throw new ForbiddenException("the limit of participants in the event has been reached");
        }

        Participation newParticipation = Participation.builder()
                .created(LocalDateTime.now())
                .event(event)
                .requester(user)
                .build();

        if (event.getRequestModeration().equals(false)) {
            newParticipation.setState(ParticipationState.CONFIRMED);
        } else {
            newParticipation.setState(ParticipationState.PENDING);
        }

        newParticipation = participationRepo.save(newParticipation);
        return ParticipationMapper.toDto(newParticipation);
    }

    @Override
    @Transactional
    public ParticipationDto cancelParticipation(Long requesterId, Long requestId) {
        Participation participation = participationRepo.findByRequesterIdAndId(requesterId, requestId)
                .orElseThrow(() -> new NotFoundException("Participation not found requesterId: "
                        + requesterId + " requestId: " + requestId));
        participation.setState(ParticipationState.CANCELED);
        return ParticipationMapper.toDto(participation);
    }
}
