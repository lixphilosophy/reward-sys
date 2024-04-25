package org.assessment.service;

import org.assessment.domain.PointSummaryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assessment.entity.Transaction;
import org.assessment.repository.TransactionRepository;
import org.assessment.service.impl.RewardServiceImpl;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RewardControllerTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardServiceImpl rewardService;

    private Transaction transaction1, transaction2;

    @BeforeEach
    void setUp() {
        transaction1 = new Transaction("1", "100", "John", "Doe", 120, 90, ZonedDateTime.now());
        transaction2 = new Transaction("2", "101", "Jane", "Roe", 150,150, ZonedDateTime.now());
    }

    @Test
    void testGetAllPointSummariesWithNoTransactions() {
        when(transactionRepository.findAll()).thenReturn(List.of());

        List<PointSummaryDto> result = rewardService.getAllPointSummaries();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllPointSummariesWithMultipleTransactions() {
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        List<PointSummaryDto> result = rewardService.getAllPointSummaries();

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals(90, result.get(0).getTotalPoints());

        // Check the details of the first customer
        PointSummaryDto firstCustomerSummary = result.get(0);
        assertEquals("John", firstCustomerSummary.getCustomerFirstName());
        assertEquals("Doe", firstCustomerSummary.getCustomerLastName());
        assertTrue(firstCustomerSummary.getPointsPerMonth().containsKey("2024-04"));
        assertEquals(Integer.valueOf(90), firstCustomerSummary.getPointsPerMonth().get("2024-04"));

        // Ensure proper points calculation is applied
        assertEquals(90, firstCustomerSummary.getPointsPerMonth().get("2024-04"));
    }
}
