package org.assessment.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.assessment.constant.StatusCode;
import org.assessment.domain.response.ResponseDTO;
import org.assessment.exception.transaction.InvalidRequestException;
import org.assessment.exception.transaction.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
@Slf4j
public class TransactionExceptionHandler {

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<ResponseDTO<Object, Object>> handleInvalidRequestException(InvalidRequestException e) {
        log.warn("InvalidRequestException: {}", e.getMessage());
        return ResponseEntity.badRequest().body(ResponseDTO.<Object, Object>builder()
                .code(StatusCode.FAIL)
                .msg(e.getMessage())
                .data(ResponseDTO.Data.<Object, Object>builder()
                        .ent(null)
                        .ext(null)
                        .build())
                .build());
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ResponseDTO<Object, Object>> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.warn("ResourceNotFoundException: {}", e.getMessage());
        return ResponseEntity.badRequest().body(ResponseDTO.<Object, Object>builder()
                .code(StatusCode.FAIL)
                .msg(e.getMessage())
                .data(ResponseDTO.Data.<Object, Object>builder()
                        .ent(null)
                        .ext(null)
                        .build())
                .build());
    }

    @ExceptionHandler(value = DateTimeParseException.class)
    public ResponseEntity<ResponseDTO<Object, Object>> handleDateTimeParseException(DateTimeParseException e) {
        log.warn("DateTimeParseException: {}", e.getMessage());
        return ResponseEntity.badRequest().body(ResponseDTO.<Object, Object>builder()
                .code(StatusCode.FAIL)
                .msg("Invalid date format")
                .data(ResponseDTO.Data.<Object, Object>builder()
                        .ent(null)
                        .ext(null)
                        .build())
                .build());
    }
}
