package main_service_application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Participation {
    private LocalDateTime created;
    private Long event;
    private Long id;
    private Long requester;
    private ApplicationState state;
}
