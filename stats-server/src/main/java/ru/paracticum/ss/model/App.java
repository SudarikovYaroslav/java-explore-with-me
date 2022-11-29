package ru.paracticum.ss.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "apps")
public class App {

    public static final String ID_COLUMN_NAME = "app_id";
    public static final String NAME_COLUMN_NAME = "app_name";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = NAME_COLUMN_NAME)
    private String name;

    public App(String name) {
        this.name = name;
    }
}
