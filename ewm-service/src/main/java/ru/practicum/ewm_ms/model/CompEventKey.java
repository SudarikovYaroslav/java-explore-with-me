package ru.practicum.ewm_ms.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CompEventKey implements Serializable {
    private Long compilationId;
    private Long eventId;
}
