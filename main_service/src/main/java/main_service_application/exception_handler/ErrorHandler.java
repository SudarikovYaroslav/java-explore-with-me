package main_service_application.exception_handler;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main_service_application.mappers.DateTimeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@NoArgsConstructor
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handle(Throwable ex) {
        return ApiError.builder()
                .status("INTERNAL_SERVER_ERROR")
                .reason("Error occurred")
                .message("could not execute statement; SQL [n/a]; constraint [uq_category_name];" +
                        " nested exception is org.hibernate.exception.ConstraintViolationException: " +
                        "could not execute statement")
                .timestamp(DateTimeMapper.toString(LocalDateTime.now()))
                .build();
    }
}