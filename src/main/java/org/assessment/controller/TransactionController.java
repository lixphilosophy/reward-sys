package org.assessment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assessment.constant.StatusCode;
import org.assessment.constant.StatusMsg;
import org.assessment.domain.request.AddTransactionRequest;
import org.assessment.domain.request.BatchAddTransactionRequest;
import org.assessment.domain.response.AddTransactionResponse;
import org.assessment.domain.response.BatchAddTransactionResponse;
import org.assessment.domain.response.ResponseDTO;
import org.assessment.exception.transaction.InvalidRequestException;
import org.assessment.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/transaction/api/v1")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/batch/add")
    public ResponseEntity<ResponseDTO<BatchAddTransactionResponse, Object>> batchAddRecord(
            @RequestBody BatchAddTransactionRequest request) {
        log.info("Batch add transaction: {}", request);

        for (AddTransactionRequest transaction : request.getTransactions()) {
            requestCheckHelper(transaction);
        }

        List<String> transactionIds = transactionService.batchAddTransaction(request);

        return ResponseEntity.ok().body(ResponseDTO.<BatchAddTransactionResponse, Object>builder()
                .code(StatusCode.OK)
                .msg(StatusMsg.NO_ERROR)
                .data(ResponseDTO.Data.<BatchAddTransactionResponse, Object>builder()
                        .ent(BatchAddTransactionResponse.builder()
                                .transactionIds(transactionIds)
                                .build())
                        .build())
                .build());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<AddTransactionResponse, Object>> addRecord(@RequestBody AddTransactionRequest request) {
        log.info("Adding transaction: {}", request);

        requestCheckHelper(request);

        String transactionId = transactionService.addTransaction(request);

        return ResponseEntity.ok().body(ResponseDTO.<AddTransactionResponse, Object>builder()
                .code(StatusCode.OK)
                .msg(StatusMsg.NO_ERROR)
                .data(ResponseDTO.Data.<AddTransactionResponse, Object>builder()
                        .ent(AddTransactionResponse.builder()
                                .transactionId(transactionId)
                                .build())
                        .build())
                .build());
    }

    protected void requestCheckHelper(AddTransactionRequest request) {
        if (Objects.isNull(request.getCustomerId())) {
            throw new InvalidRequestException("customerId is required");
        }

        if (Objects.isNull(request.getAmount())) {
            throw new InvalidRequestException("please provide amount customer spent");
        }

        if (Objects.isNull(request.getTransactionTime())) {
            throw new InvalidRequestException("please provide transaction time");
        }
    }
}
