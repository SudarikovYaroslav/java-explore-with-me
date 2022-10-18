package statistics_service_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import statistics_service_application.model.Statistics;

@SpringBootApplication
public class StatisticsServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(Statistics.class, args);
    }
}
