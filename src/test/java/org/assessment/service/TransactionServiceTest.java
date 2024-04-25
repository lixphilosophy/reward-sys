package org.assessment.service;


import org.assessment.domain.request.AddTransactionRequest;
import org.assessment.domain.request.BatchAddTransactionRequest;
import org.assessment.entity.Customer;
import org.assessment.entity.Transaction;
import org.assessment.exception.transaction.ResourceNotFoundException;
import org.assessment.repository.CustomerRepository;
import org.assessment.repository.TransactionRepository;
import org.assessment.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Customer customer;
    private AddTransactionRequest addTransactionRequest;

    private BatchAddTransactionRequest batchAddTransactionRequest;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        customer = new Customer("1", "John", "Doe");
        addTransactionRequest = new AddTransactionRequest();
        addTransactionRequest.setCustomerId("1");
        addTransactionRequest.setAmount(100);
        addTransactionRequest.setTransactionTime(ZonedDateTime.now());

        AddTransactionRequest addTransactionRequest2 = new AddTransactionRequest();
        addTransactionRequest2.setCustomerId("1");
        addTransactionRequest2.setAmount(150);
        addTransactionRequest2.setTransactionTime(ZonedDateTime.now().minusDays(1));

        batchAddTransactionRequest = new BatchAddTransactionRequest();
        batchAddTransactionRequest.setTransactions(Arrays.asList(addTransactionRequest, addTransactionRequest2));

        transaction = Transaction.builder()
                .transactionId("trans-123")
                .customerId("1")
                .customerFirstName("John")
                .customerLastName("Doe")
                .amount(100)
                .point(50)
                .transactionTime(ZonedDateTime.now())
                .build();
    }

    @Test
    void testAddTransactionCustomerNotFound() {
        when(customerRepository.findById("1")).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () ->
                transactionService.addTransaction(addTransactionRequest));

        assertEquals("Customer not found with id: 1", exception.getMessage());
    }

    @Test
    void testAddTransactionSuccessful() {
        when(customerRepository.findById("1")).thenReturn(Optional.of(customer));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        transactionService.addTransaction(addTransactionRequest);

        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void testBatchAddTransactionWithInvalidIds() {
        when(customerRepository.findAllById(anyList())).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(ResourceNotFoundException.class, () ->
                transactionService.batchAddTransaction(batchAddTransactionRequest));

        assertTrue(exception.getMessage().contains("request contains invalid customer id"));
    }

    @Test
    void testBatchAddTransactionSuccess() {
        // Set up a scenario where all customers exist and transactions should succeed.
        when(customerRepository.findAllById(any())).thenReturn(Collections.singletonList(customer));
        when(transactionRepository.saveAll(any())).thenAnswer(i -> i.getArgument(0));


        List<String> transactionIds = transactionService.batchAddTransaction(batchAddTransactionRequest);

        // Verify the outcome
        assertNotNull(transactionIds);
        assertEquals(2, transactionIds.size());
        verify(transactionRepository, times(1)).saveAll(any());
    }
}
