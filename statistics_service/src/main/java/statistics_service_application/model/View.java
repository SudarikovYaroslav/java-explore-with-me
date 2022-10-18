package statistics_service_application.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class View {
    public static final String ID_COLUMN_NAME = "id";
    public static final String IP_COLUMN_NAME = "ip";
    public static final String PATH_COLUMN_NAME = "path";
    public static final String DATE_COLUMN_NAME = "date";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue
    private Long id;
    @Column(name = PATH_COLUMN_NAME, nullable = false)
    private String path;
    @Column(name = IP_COLUMN_NAME, nullable = false)
    private String ip;
    @Column(name = DATE_COLUMN_NAME, nullable = false)
    private LocalDateTime date;
}
