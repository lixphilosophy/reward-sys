package org.assessment.entity;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void testTransactionSettersAndGetters() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId("T1001");
        transaction.setCustomerId("C1001");
        transaction.setCustomerFirstName("John");
        transaction.setCustomerLastName("Doe");
        transaction.setAmount(500);
        transaction.setPoint(50);
        transaction.setTransactionTime(ZonedDateTime.now(ZoneId.of("UTC")));

        assertEquals("T1001", transaction.getTransactionId());
        assertEquals("C1001", transaction.getCustomerId());
        assertEquals("John", transaction.getCustomerFirstName());
        assertEquals("Doe", transaction.getCustomerLastName());
        assertEquals(500, transaction.getAmount());
        assertEquals(50, transaction.getPoint());
        assertNotNull(transaction.getTransactionTime());
    }

    @Test
    public void testTransactionBuilder() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        Transaction transaction = Transaction.builder()
                .transactionId("T1002")
                .customerId("C1002")
                .customerFirstName("Alice")
                .customerLastName("Smith")
                .amount(1000)
                .point(100)
                .transactionTime(now)
                .build();

        assertEquals("T1002", transaction.getTransactionId());
        assertEquals("C1002", transaction.getCustomerId());
        assertEquals("Alice", transaction.getCustomerFirstName());
        assertEquals("Smith", transaction.getCustomerLastName());
        assertEquals(1000, transaction.getAmount());
        assertEquals(100, transaction.getPoint());
        assertEquals(now, transaction.getTransactionTime());

        System.out.println(Transaction.builder().build().toString());
    }
}
