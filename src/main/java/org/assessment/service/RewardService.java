package org.assessment.service;

import org.assessment.domain.PointSummaryDto;

import java.util.List;

public interface RewardService {

    List<PointSummaryDto> getAllPointSummaries();
}
