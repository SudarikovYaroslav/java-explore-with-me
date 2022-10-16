package statistics_service_application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Statistics {
    private long compilationAccessing;
    private long detailedEventAccessing;
}
