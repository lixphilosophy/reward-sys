package org.assessment.domain;

import org.assessment.domain.response.BatchAddTransactionResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class BatchAddTransactionResponseTest {

    @Test
    public void testBuilderAndAccessors() {
        // Use the builder to create an instance
        List<String> transactionIds = Arrays.asList("TX123", "TX456");
        BatchAddTransactionResponse response = BatchAddTransactionResponse.builder()
                .transactionIds(transactionIds)
                .build();

        // Assertions to check the builder functionality
        assertNotNull(response.getTransactionIds());
        assertEquals(2, response.getTransactionIds().size());
        assertEquals("TX123", response.getTransactionIds().get(0));
        assertEquals("TX456", response.getTransactionIds().get(1));
    }

    @Test
    public void testSettersAndGetters() {
        // Create an instance using the no-args constructor
        BatchAddTransactionResponse response = new BatchAddTransactionResponse();

        // Set transaction IDs
        List<String> transactionIds = Arrays.asList("TX789", "TX012");
        response.setTransactionIds(transactionIds);

        // Test getters
        assertNotNull(response.getTransactionIds());
        assertEquals(2, response.getTransactionIds().size());
        assertEquals("TX789", response.getTransactionIds().get(0));
        assertEquals("TX012", response.getTransactionIds().get(1));
    }
}
