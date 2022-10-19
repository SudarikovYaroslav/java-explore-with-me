package ru.paracticum.ewm_ss.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    private String app;
    private String uri;
    private Long hits;
}