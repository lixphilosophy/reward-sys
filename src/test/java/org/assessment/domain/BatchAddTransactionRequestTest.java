package org.assessment.domain;

import org.assessment.domain.request.AddTransactionRequest;
import org.assessment.domain.request.BatchAddTransactionRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class BatchAddTransactionRequestTest {

    @Test
    public void testNoArgsConstructor() {
        BatchAddTransactionRequest request = new BatchAddTransactionRequest();
        assertNull(request.getTransactions());
    }

    @Test
    public void testAllArgsConstructor() {
        AddTransactionRequest transaction1 = new AddTransactionRequest("C123", 100, null);
        AddTransactionRequest transaction2 = new AddTransactionRequest("C456", 200, null);
        List<AddTransactionRequest> transactions = Arrays.asList(transaction1, transaction2);

        BatchAddTransactionRequest request = new BatchAddTransactionRequest(transactions);

        assertNotNull(request.getTransactions());
        assertEquals(2, request.getTransactions().size());
        assertSame(transactions, request.getTransactions());
    }

    @Test
    public void testSettersAndGetters() {
        BatchAddTransactionRequest request = new BatchAddTransactionRequest();
        AddTransactionRequest transaction = new AddTransactionRequest("C123", 100, null);
        List<AddTransactionRequest> transactions = Arrays.asList(transaction);

        request.setTransactions(transactions);

        assertNotNull(request.getTransactions());
        assertEquals(1, request.getTransactions().size());
        assertSame(transaction, request.getTransactions().get(0));
    }

    @Test
    public void testBuilder() {
        List<AddTransactionRequest> transactions = Arrays.asList(
                new AddTransactionRequest("C789", 300, null),
                new AddTransactionRequest("C012", 400, null)
        );
        BatchAddTransactionRequest request = BatchAddTransactionRequest.builder()
                .transactions(transactions)
                .build();
        assertNotNull(request.getTransactions());
        assertEquals(2, request.getTransactions().size());
    }
}