package ru.paracticum.ewm_ss.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto {
    private String app;
    private String uri;
    private Long hits;
}
