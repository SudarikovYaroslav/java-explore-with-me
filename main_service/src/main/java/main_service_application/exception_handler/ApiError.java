package main_service_application.exception_handler;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private Error[] errors; // тут не очень понятно какой должен быть тип массива
    private String message;
    private String reason;
    private String status;
    private String timestamp;
}
