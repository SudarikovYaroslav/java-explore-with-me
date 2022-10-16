package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Compilation {
    private Event[] events;
    private Long id;
    private Boolean pinned;
    private String title;
}
