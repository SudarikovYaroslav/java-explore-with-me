package main_service_application.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compilation {

    public static final String ID_COLUMN_NAME = "compilation_id";
    public static final String PINNED_COLUMN_NAME = "pinned";
    public static final String TITLE_COLUMN_NAME = "title";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn
    private Event[] events;
    @Column(name = PINNED_COLUMN_NAME, nullable = false )
    private Boolean pinned;
    @Column(name = TITLE_COLUMN_NAME, nullable = false)
    private String title;
}
