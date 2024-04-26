package org.assessment.repository;

import org.assessment.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

    List<Transaction> findByCustomerFirstNameContainingOrCustomerLastNameContaining(String firstName, String lastName);
}
