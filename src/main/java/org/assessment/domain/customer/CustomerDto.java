package org.assessment.domain.customer;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String customerId;
    private String customerFirstName;
    private String customerLastName;
}
