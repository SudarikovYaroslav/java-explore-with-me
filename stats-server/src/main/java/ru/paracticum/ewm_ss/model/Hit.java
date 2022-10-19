package ru.paracticum.ewm_ss.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "hits")
public class Hit {
    public static final String ID_COLUMN_NAME = "id";
    public static final String IP_COLUMN_NAME = "ip";
    public static final String APP_COLUMN_NAME = "app";
    public static final String URI_COLUMN_NAME = "uri";
    public static final String TIMESTAMP_COLUMN_NAME = "date";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue
    private Long id;
    @Column(name = APP_COLUMN_NAME, nullable = false)
    private String app;
    @Column(name = URI_COLUMN_NAME)
    private String uri;
    @Column(name = IP_COLUMN_NAME, nullable = false)
    private String ip;
    @Column(name = TIMESTAMP_COLUMN_NAME, nullable = false)
    private LocalDateTime timeStamp;
}