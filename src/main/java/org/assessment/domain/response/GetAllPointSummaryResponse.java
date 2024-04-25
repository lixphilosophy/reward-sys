package org.assessment.domain.response;

import lombok.*;
import org.assessment.domain.reward.PointSummaryDto;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPointSummaryResponse {
    List<PointSummaryDto> pointSummaries;
}
