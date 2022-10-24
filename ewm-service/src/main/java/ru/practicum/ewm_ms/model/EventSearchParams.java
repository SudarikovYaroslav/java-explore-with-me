package ru.practicum.ewm_ms.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm_ms.mappers.DateTimeMapper;
import ru.practicum.ewm_ms.util.Util;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EventSearchParams {
    private List<Long> userIds;
    private String text;
    private List<PublicationState> states;
    private List<Long> categories;
    private LocalDateTime rangeStart;
    private LocalDateTime rangeEnd;
    private Boolean paid;
    private Boolean onlyAvailable;
    private Integer from;
    private Integer size;
    private EventSort sort;

//    public EventSearchParams(List<Long> userIds,
//                            String text,
//                            List<String> states,
//                            List<Long> categories,
//                            String rangeStart,
//                            String rangeEnd,
//                            Boolean paid,
//                            Boolean onlyAvailable,
//                            Integer from,
//                            Integer size,
//                            String sort) {
//        this.userIds = userIds;
//        if (text != null) {
//            this.text = text.toLowerCase();
//        }
//        if (states != null) {
//            this.states = Util.mapToStates(states);
//        }
//        this.categories = categories;
//        if (rangeStart != null) {
//            this.rangeStart = DateTimeMapper.toDateTime(rangeStart);
//        }
//        if (rangeEnd != null) {
//            this.rangeEnd = DateTimeMapper.toDateTime(rangeEnd);
//        }
//        this.paid = paid;
//        this.onlyAvailable = onlyAvailable;
//        this.from = from;
//        this.size = size;
//        if (sort != null) {
//            this.sort = Util.parseSort(sort);
//        }
//    }

    public EventSearchParams(List<Long> usersIds,
                            List<String> states,
                            List<Long> categories,
                            String rangeStart,
                            String rangeEnd,
                            Integer from,
                            Integer size) {
        this.userIds = usersIds;
        this.states = Util.mapToStates(states);
        this.categories = categories;
        if (rangeStart != null) {
            this.rangeStart = DateTimeMapper.toDateTime(rangeStart);
        }
        if (rangeEnd != null) {
            this.rangeEnd = DateTimeMapper.toDateTime(rangeEnd);
        }
        this.from = from;
        this.size = size;
    }

    public EventSearchParams(String text,
                             List<Long> categories,
                             Boolean paid,
                             String rangeStart,
                             String rangeEnd,
                             Boolean onlyAvailable,
                             String sort,
                             Integer from,
                             Integer size) {
        if (text != null) {
            this.text = text.toLowerCase();
        }
        this.categories = categories;
        this.paid = paid;
        if (rangeStart != null) {
            this.rangeStart = DateTimeMapper.toDateTime(rangeStart);
        }
        if (rangeEnd != null) {
            this.rangeEnd = DateTimeMapper.toDateTime(rangeEnd);
        }
        this.onlyAvailable = onlyAvailable;
        if (sort != null) {
            this.sort = Util.parseSort(sort);
        }
        this.from = from;
        this.size = size;
    }
}