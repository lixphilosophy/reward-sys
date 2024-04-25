package org.assessment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "transaction")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "customer_first_name")
    private String customerFirstName;

    @Column(name = "customer_last_name")
    private String customerLastName;

    @Column(name = "amount")
    private int amount;

    @Column(name = "point")
    private int point;

    @Column(name = "transaction_time")
    private ZonedDateTime transactionTime;
}
