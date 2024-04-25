package org.assessment.exception;

import org.assessment.domain.response.ResponseDTO;
import org.assessment.exception.customer.CustomerRequestException;
import org.assessment.exception.handler.CustomExceptionHandler;
import org.assessment.exception.transaction.TransactionRequestException;
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
public class CustomExceptionHandlerTest {

    @InjectMocks
    private CustomExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
    }

    @Test
    void handleInvalidRequestException() {
        TransactionRequestException exception = new TransactionRequestException("Invalid request");
        ResponseEntity<ResponseDTO<Object, Object>> response = exceptionHandler.handleInvalidRequestException(exception);
        assertNotNull(response);
        assertEquals("fail", Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("Invalid request", Objects.requireNonNull(response.getBody()).getMsg());
    }

    @Test
    void handleResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");
        ResponseEntity<ResponseDTO<Object, Object>> response = exceptionHandler.handleResourceNotFoundException(exception);
        assertNotNull(response);
        assertEquals("fail", Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("Resource not found", Objects.requireNonNull(response.getBody()).getMsg());
    }

    @Test
    void handleDateTimeParseException() {
        DateTimeParseException exception = new DateTimeParseException("Invalid date format", "2024-04-12", 0);
        ResponseEntity<ResponseDTO<Object, Object>> response = exceptionHandler.handleDateTimeParseException(exception);
        assertNotNull(response);
        assertEquals("fail", Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("Invalid date format", Objects.requireNonNull(response.getBody()).getMsg());
    }

    @Test
    void handleCustomerRequestException() {
        CustomerRequestException exception = new CustomerRequestException("Invalid request");
        ResponseEntity<ResponseDTO<Object, Object>> response = exceptionHandler.handleCustomerRequestException(exception);
        assertNotNull(response);
        assertEquals("fail", Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("Invalid request", Objects.requireNonNull(response.getBody()).getMsg());
    }
}