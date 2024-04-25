package org.assessment.service;

import org.assessment.domain.request.AddTransactionRequest;
import org.assessment.domain.request.BatchAddTransactionRequest;
import org.assessment.domain.response.BatchAddTransactionResponse;

import java.util.List;

public interface TransactionService {
    String addTransaction(AddTransactionRequest request);

    List<String> batchAddTransaction(BatchAddTransactionRequest request);
}
