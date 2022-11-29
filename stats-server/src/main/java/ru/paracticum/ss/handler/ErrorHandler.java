package ru.paracticum.ss.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.paracticum.ss.mapper.DateTimeMapper;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handle(Throwable ex) {
        log.info("Unexpected internal server error", ex);
        ex.printStackTrace();
        return ApiError.builder()
                .message(ex.getMessage())
                .reason("Error occurred")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(DateTimeMapper.toString(LocalDateTime.now()))
                .build();
    }
}
