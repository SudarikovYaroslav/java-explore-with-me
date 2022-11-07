package ru.practicum.ms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ms.dto.ParticipationDto;
import ru.practicum.ms.exception.ForbiddenException;
import ru.practicum.ms.exception.NotFoundException;
import ru.practicum.ms.handler.mappers.ParticipationMapper;
import ru.practicum.ms.model.*;
import ru.practicum.ms.repository.EventRepository;
import ru.practicum.ms.repository.ParticipationRepository;
import ru.practicum.ms.repository.UserRepository;
import ru.practicum.ms.service.ParticipationService;
import ru.practicum.ms.util.Util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepo;
    private final EventRepository eventRepo;
    private final UserRepository userRepo;

    @Override
    public List<ParticipationDto> getInfoAboutAllParticipation(Long userId) {
        userRepo.findById(userId).orElseThrow(() -> new NotFoundException(Util.getUserNotFoundMessage(userId)));
        List<Participation> participations = participationRepo.findAllByRequesterId(userId);
        return participations.stream().map(ParticipationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ParticipationDto addParticipationQuery(Long userId, Long eventId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException(Util.getUserNotFoundMessage(userId)));
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new NotFoundException(Util.getEventNotFoundMessage(eventId)));

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
        int confirmedRequests = participationRepo.getConfirmedRequests(event.getId(), ParticipationState.CONFIRMED);
        if (event.getParticipantLimit() != 0 && confirmedRequests >= event.getParticipantLimit()) {
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
