package ru.practicum.ms.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comments")
public class Comment {

    public static final String ID_COLUMN_NAME = "comment_id";
    public static final String OWNER_COLUMN_NAME = "owner_id";
    public static final String EVENT_COLUMN_NAME = "event_id";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne(optional = false)
    @JoinColumn(name = OWNER_COLUMN_NAME, nullable = false)
    private User owner;
    @ManyToOne(optional = false)
    @JoinColumn(name = EVENT_COLUMN_NAME, nullable = false)
    private Event event;
    private LocalDateTime date;
}
