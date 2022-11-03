package ru.practicum.ms.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ms.model.EventSort;
import ru.practicum.ms.model.PublicationState;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Util {

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
        return  "Participation request with id=" + requestId + " was not found.";
    }

    public static EventSort parseSort(String str) {
        EventSort sort;
        try {
            sort = EventSort.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("invalid Sort value: " + str);
        }
        return sort;
    }

    public static List<PublicationState> mapToStates(List<String> stateNames) {
        List<PublicationState> result = new ArrayList<>();
        for (String name : stateNames) {
            PublicationState state = PublicationState.valueOf(name.toUpperCase());
            result.add(state);
        }
        return result;
    }
}
