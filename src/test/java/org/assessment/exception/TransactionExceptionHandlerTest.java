package org.assessment.exception;

import org.assessment.domain.response.ResponseDTO;
import org.assessment.exception.handler.TransactionExceptionHandler;
import org.assessment.exception.transaction.InvalidRequestException;
import org.assessment.exception.transaction.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.format.DateTimeParseException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransactionExceptionHandlerTest {

    @InjectMocks
    private TransactionExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
    }

    @Test
    void handleInvalidRequestException() {
        InvalidRequestException exception = new InvalidRequestException("Invalid request");
        ResponseEntity<ResponseDTO<Object, Object>> response = exceptionHandler.handleInvalidRequestException(exception);
        assertNotNull(response);
        assertEquals("fail", Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("Invalid request", Objects.requireNonNull(response.getBody()).getMsg());
    }

    @Test
    void handleResourceNotFoundException() {
        // Setup
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");

        // Execute
        ResponseEntity<ResponseDTO<Object, Object>> response = exceptionHandler.handleResourceNotFoundException(exception);

        // Verify
        assertNotNull(response);
        assertEquals("fail", Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("Resource not found", Objects.requireNonNull(response.getBody()).getMsg());
    }

    @Test
    void handleDateTimeParseException() {
        // Setup
        DateTimeParseException exception = new DateTimeParseException("Invalid date format", "2024-04-12", 0);

        // Execute
        ResponseEntity<ResponseDTO<Object, Object>> response = exceptionHandler.handleDateTimeParseException(exception);

        // Verify
        assertNotNull(response);
        assertEquals("fail", Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("Invalid date format", Objects.requireNonNull(response.getBody()).getMsg());
    }
}