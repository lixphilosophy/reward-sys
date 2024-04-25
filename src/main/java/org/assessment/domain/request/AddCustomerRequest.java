package org.assessment.domain.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCustomerRequest {
    private String customerFirstName;
    private String customerLastName;
}
