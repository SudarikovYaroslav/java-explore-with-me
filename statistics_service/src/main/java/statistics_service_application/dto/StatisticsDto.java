package statistics_service_application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import statistics_service_application.model.View;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StatisticsDto {
    private List<View> views;

    public long getViewCount() {
        return views.size();
    }
}
