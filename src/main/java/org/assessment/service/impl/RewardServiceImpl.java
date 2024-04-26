package org.assessment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assessment.domain.reward.PointSummaryDto;
import org.assessment.entity.Transaction;
import org.assessment.repository.TransactionRepository;
import org.assessment.service.RewardService;
import org.assessment.utils.Calculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class RewardServiceImpl implements RewardService {

    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public List<PointSummaryDto> getAllPointSummaries(String searchContent) {

        List<Transaction> transactions = Objects.isNull(searchContent) || searchContent.isEmpty() ?
                (List<Transaction>) transactionRepository.findAll() :
                transactionRepository.findByCustomerFirstNameContainingOrCustomerLastNameContaining(searchContent, searchContent);
        return getPointsSummaries(transactions);
    }

    private List<PointSummaryDto> getPointsSummaries(List<Transaction> transactions) {
        Map<String, List<Transaction>> transactionsByCustomer = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId));

        return transactionsByCustomer.entrySet().stream().map(entry -> {
            String customerId = entry.getKey();
            List<Transaction> customerTransactions = entry.getValue();
            String firstName = customerTransactions.get(0).getCustomerFirstName();
            String lastName = customerTransactions.get(0).getCustomerLastName();

            Map<String, Integer> pointsPerMonth = customerTransactions.stream()
                    .collect(Collectors.groupingBy(
                            transaction -> YearMonth.from(transaction.getTransactionTime()).format(DateTimeFormatter.ofPattern("yyyy-MM")),
                            Collectors.summingInt(transaction -> Calculator.calculatePoints(transaction.getAmount()))
                    ));

            int totalPoints = pointsPerMonth.values().stream().mapToInt(Integer::intValue).sum();

            return PointSummaryDto.builder()
                    .customerId(customerId)
                    .customerFirstName(firstName)
                    .customerLastName(lastName)
                    .pointsPerMonth(pointsPerMonth)
                    .totalPoints(totalPoints)
                    .build();
        }).collect(Collectors.toList());
    }
}
