package org.assessment.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTransactionRequest {
    private String customerId;
    private Integer amount;
    private ZonedDateTime transactionTime;
}
