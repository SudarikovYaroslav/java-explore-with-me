package ru.paracticum.ewm_ss.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.paracticum.ewm_ss.mapper.DateTimeMapper;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handle(Throwable ex) {
        ex.printStackTrace();
        return ApiError.builder()
                .message(ex.getMessage())
                .reason("Error occurred")
                .status("INTERNAL_SERVER_ERROR")
                .timestamp(DateTimeMapper.toString(LocalDateTime.now()))
                .build();
    }
}
