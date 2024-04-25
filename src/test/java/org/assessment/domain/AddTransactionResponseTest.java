package org.assessment.domain;

import org.assessment.domain.response.AddTransactionResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddTransactionResponseTest {

    @Test
    public void testBuilderAndAccessors() {
        String transactionId = "TX123456";
        AddTransactionResponse response = AddTransactionResponse.builder()
                .transactionId(transactionId)
                .build();

        response.setTransactionId(transactionId);

        assertNotNull(response.getTransactionId());
        assertEquals(transactionId, response.getTransactionId());
    }
}
