package ru.paracticum.ewm_ss.exception_handler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiError {
    private Error[] errors;
    private String message;
    private String reason;
    private String status;
    private LocalDateTime timestamp;
}
