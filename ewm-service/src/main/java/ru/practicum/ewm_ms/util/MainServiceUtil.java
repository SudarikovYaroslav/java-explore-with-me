package ru.practicum.ewm_ms.util;

import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.model.Compilation;
import ru.practicum.ewm_ms.model.Event;
import ru.practicum.ewm_ms.model.Participation;
import ru.practicum.ewm_ms.model.User;
import ru.practicum.ewm_ms.repository.CompilationRepository;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.repository.ParticipationRepository;
import ru.practicum.ewm_ms.repository.UserRepository;

public class MainServiceUtil {

    private MainServiceUtil() {
    }

    public static String getUserNotFoundMessage(long userId) {
        return "User with id=" + userId + " was not found.";
    }

    public static String getEventNotFoundMessage(long eventId) {
        return "Event with id=" + eventId + " was not found.";
    }

    public static String getCategoryNotFoundMessage(long categoryId) {
        return "Category with id=" + categoryId + " was not found";
    }

    public static String getCompilationNotFoundMessage(long compilationId) {
        return "Compilation with id:=" + compilationId + " was not found";
    }

    public static String getParticipationNotFoundMessage(long requestId) {
        return  "Participation request with id=" + requestId +" was not found.";
    }

    public static User checkIfUserExists(Long userId, UserRepository repo) {
        User user = repo.findById(userId).orElse(null);
        if (user == null) {
            throw new NotFoundException(getUserNotFoundMessage(userId));
        }
        return user;
    }

    public static Event checkIfEventExists(Long eventId, EventRepository repo) {
        Event event = repo.findById(eventId).orElse(null);
        if (event == null) {
            throw new NotFoundException(getEventNotFoundMessage(eventId));
        }
        return event;
    }

    public static Participation checkIfParticipationRequestExists(Long requestId, ParticipationRepository repo) {
        Participation par = repo.findById(requestId).orElse(null);
        if (par == null) {
            throw new NotFoundException(getParticipationNotFoundMessage(requestId));
        }
        return par;
    }

    public static Compilation cheIfCompilationExists(Long compId, CompilationRepository repo) {
        Compilation compilation = repo.findById(compId).orElse(null);
        if (compilation == null) {
            throw new NotFoundException(getCompilationNotFoundMessage(compId));
        }
        return compilation;
    }
}
