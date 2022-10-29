package ru.paracticum.ewm_ss.exception_handler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ApiError {
    private List<Error> errors;
    private String message;
    private String reason;
    private String status;
    private String timestamp;
}
