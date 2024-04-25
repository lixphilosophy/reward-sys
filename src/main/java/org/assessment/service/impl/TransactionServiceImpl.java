package org.assessment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assessment.domain.request.AddTransactionRequest;
import org.assessment.domain.request.BatchAddTransactionRequest;
import org.assessment.entity.Customer;
import org.assessment.entity.Transaction;
import org.assessment.exception.transaction.ResourceNotFoundException;
import org.assessment.repository.CustomerRepository;
import org.assessment.repository.TransactionRepository;
import org.assessment.service.TransactionService;
import org.assessment.utils.Calculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public String addTransaction(AddTransactionRequest request) {

        Optional<Customer> customerOptional = customerRepository.findById(request.getCustomerId());

        if (customerOptional.isEmpty()) {
            log.error("Customer not found with id: {}", request.getCustomerId());
            throw new ResourceNotFoundException("Customer not found with id: " + request.getCustomerId());
        }

        Customer customer = customerOptional.get();

        String transactionId = UUID.randomUUID().toString();

        Transaction transaction = Transaction.builder()
                .transactionId(transactionId)
                .customerId(customer.getCustomerId())
                .customerFirstName(customer.getFirstName())
                .customerLastName(customer.getLastName())
                .amount(request.getAmount())
                .point(Calculator.calculatePoints(request.getAmount()))
                .transactionTime(request.getTransactionTime())
                .build();

        transactionRepository.save(transaction);

        return transactionId;
    }

    @Override
    @Transactional
    public List<String> batchAddTransaction(BatchAddTransactionRequest request) {
        List<String> customerIds = request.getTransactions()
                .stream()
                .map(AddTransactionRequest::getCustomerId)
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .stream().toList();

        List<Customer> customers = (List<Customer>) customerRepository.findAllById(customerIds);

        if (customers.size() != customerIds.size()) {
            log.error("One of the id not found: {}", customerIds);
            throw new ResourceNotFoundException("request contains invalid customer id");
        }

        List<Transaction> transactions = new ArrayList<>();
        for (AddTransactionRequest addRequest : request.getTransactions()) {
            Customer customer = customers.stream()
                    .filter(c -> c.getCustomerId().equals(addRequest.getCustomerId()))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + addRequest.getCustomerId()));

            int points = Calculator.calculatePoints(addRequest.getAmount());
            Transaction transaction = Transaction.builder()
                    .transactionId(UUID.randomUUID().toString())
                    .customerId(customer.getCustomerId())
                    .customerFirstName(customer.getFirstName())
                    .customerLastName(customer.getLastName())
                    .amount(addRequest.getAmount())
                    .point(points)
                    .transactionTime(addRequest.getTransactionTime())
                    .build();
            transactions.add(transaction);
        }

        List<Transaction> res = (List<Transaction>) transactionRepository.saveAll(transactions);

        return res.stream().map(Transaction::getTransactionId).collect(Collectors.toList());
    }


}
