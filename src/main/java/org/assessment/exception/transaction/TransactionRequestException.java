package org.assessment.exception.transaction;

public class TransactionRequestException extends RuntimeException {
    public TransactionRequestException(String message) {
        super(message);
    }
}
