package exception_handler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiError {
    private Error[] errors; // тут не очень понятно какой должен быть тип массива
    private String message;
    private String reason;
    private String status;
    private LocalDateTime timestamp;
}
