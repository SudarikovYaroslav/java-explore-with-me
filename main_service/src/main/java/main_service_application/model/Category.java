package main_service_application.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    public static final String ID_COLUMN_NAME = "category_id";
    public static final String NAME_COLUMN_NAME = "name";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue
    private Long id;
    @Column(name = NAME_COLUMN_NAME)
    private String name;
}
