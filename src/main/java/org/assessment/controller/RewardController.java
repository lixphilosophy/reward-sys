package org.assessment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assessment.constant.StatusCode;
import org.assessment.constant.StatusMsg;
import org.assessment.domain.reward.PointSummaryDto;
import org.assessment.domain.response.GetAllPointSummaryResponse;
import org.assessment.domain.response.ResponseDTO;
import org.assessment.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reward/api/v1")
@RequiredArgsConstructor
@Slf4j
public class RewardController {

    private final RewardService rewardService;

    @GetMapping("/pointSummary/getAll")
    public ResponseEntity<ResponseDTO<GetAllPointSummaryResponse, Object>> getAllCustomersPointSummaries(
            @RequestParam(value = "searchContent", required = false) String searchContent) {
        log.info("Getting point summary for all user");

        List<PointSummaryDto> pointSummaries = rewardService.getAllPointSummaries(searchContent);

        return ResponseEntity.ok().body(ResponseDTO.<GetAllPointSummaryResponse, Object>builder()
                .code(StatusCode.OK)
                .msg(StatusMsg.NO_ERROR)
                .data(ResponseDTO.Data.<GetAllPointSummaryResponse, Object>builder()
                        .ent(GetAllPointSummaryResponse.builder()
                                .pointSummaries(pointSummaries)
                                .build())
                        .build())
                .build());
    }

}
