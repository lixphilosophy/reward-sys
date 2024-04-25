package org.assessment.service;

import org.assessment.domain.reward.PointSummaryDto;

import java.util.List;

public interface RewardService {

    List<PointSummaryDto> getAllPointSummaries();
}
