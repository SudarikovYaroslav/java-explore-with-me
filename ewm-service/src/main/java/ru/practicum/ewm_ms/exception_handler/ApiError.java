package ru.practicum.ewm_ms.exception_handler;

import lombok.*;

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
    private Status status;
    private String timestamp;
}
