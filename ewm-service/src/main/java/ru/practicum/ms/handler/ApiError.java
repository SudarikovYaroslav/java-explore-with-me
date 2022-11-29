package ru.practicum.ms.handler;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private List<Error> errors;
    private String message;
    private String reason;
    private HttpStatus status;
    private String timestamp;
}
