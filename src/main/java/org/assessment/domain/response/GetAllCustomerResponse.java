package org.assessment.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.assessment.domain.customer.CustomerDto;

import java.util.List;

@Builder
@Getter
@Setter
public class GetAllCustomerResponse {
    List<CustomerDto> customers;
}
