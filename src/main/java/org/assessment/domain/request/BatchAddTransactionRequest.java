package org.assessment.domain.request;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BatchAddTransactionRequest {
    List<AddTransactionRequest> transactions;
}
