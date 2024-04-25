package org.assessment.domain;

import org.assessment.domain.request.AddTransactionRequest;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AddTransactionRequestTest {

    @Test
    public void testNoArgsConstructor() {
        AddTransactionRequest request = new AddTransactionRequest();
        assertNull(request.getCustomerId());
        assertNull(request.getAmount());
        assertNull(request.getTransactionTime());
    }

    @Test
    public void testAllArgsConstructor() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        AddTransactionRequest request = new AddTransactionRequest("C123", 500, now);

        assertEquals("C123", request.getCustomerId());
        assertEquals(Integer.valueOf(500), request.getAmount());
        assertEquals(now, request.getTransactionTime());
    }

    @Test
    public void testBuilder() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        AddTransactionRequest request = AddTransactionRequest.builder()
                .customerId("C123")
                .amount(500)
                .transactionTime(now)
                .build();

        assertEquals("C123", request.getCustomerId());
        assertEquals(Integer.valueOf(500), request.getAmount());
        assertEquals(now, request.getTransactionTime());
    }

    @Test
    public void testSettersAndGetters() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        AddTransactionRequest request = new AddTransactionRequest();

        request.setCustomerId("C123");
        request.setAmount(500);
        request.setTransactionTime(now);

        assertEquals("C123", request.getCustomerId());
        assertEquals(Integer.valueOf(500), request.getAmount());
        assertEquals(now, request.getTransactionTime());
    }

}
