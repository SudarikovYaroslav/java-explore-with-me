package main_service_application.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    public static final String ID_COLUMN_NAME = "location_id";
    public static final String LAT_COLUMN_NAME = "lat";
    public static final String LON_COLUMN_NAME = "lon";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue
    private Long id;
    @Column(name = LAT_COLUMN_NAME)
    private double lat;
    @Column(name = LON_COLUMN_NAME)
    private double lon;
}
