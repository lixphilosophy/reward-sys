package org.assessment.domain;

import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointSummaryDto {
    private String customerId;
    private String customerFirstName;
    private String customerLastName;
    private Map<String, Integer> pointsPerMonth;
    private int totalPoints;
}
