package org.assessment.exception.customer;

public class CustomerRequestException extends RuntimeException {
    public CustomerRequestException(String message) {
        super(message);
    }
}
