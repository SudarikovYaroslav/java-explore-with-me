package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Compilation {
    private Event[] events;
    private long id;
    private boolean pinned;
    private String title;
}
