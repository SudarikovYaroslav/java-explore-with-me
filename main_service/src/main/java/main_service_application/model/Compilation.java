package main_service_application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Compilation {
    private Event[] events;
    private Long id;
    private Boolean pinned;
    private String title;
}
