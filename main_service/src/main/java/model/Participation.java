package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Participation {
    private LocalDateTime created;
    private Long event;
    private Long id;
    private Long requester;
    private ApplicationState state;
}
