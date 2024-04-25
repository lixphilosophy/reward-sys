package org.assessment.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AddCustomerResponse {
    String customerId;
}
