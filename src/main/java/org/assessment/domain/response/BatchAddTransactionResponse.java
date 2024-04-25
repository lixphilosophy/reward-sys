package org.assessment.domain.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatchAddTransactionResponse {
    List<String> transactionIds;
}
