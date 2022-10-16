package main_service_application.util;

public class NotFoundMessageGen {

    private NotFoundMessageGen() {
    }

    public static String getUserNotFoundMessage(long userId) {
        return "User with id=" + userId + " was not found.";
    }

    public static String getEventNotFoundMessage(long eventId) {
        return "Event with id=" + eventId + " was not found.";
    }
}
