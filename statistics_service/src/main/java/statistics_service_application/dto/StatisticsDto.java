package statistics_service_application.dto;

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
