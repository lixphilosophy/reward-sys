package org.assessment.domain.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AddTransactionResponse {
    private String transactionId;
}
