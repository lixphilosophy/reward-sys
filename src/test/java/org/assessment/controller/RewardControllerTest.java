package org.assessment.controller;

import org.assessment.constant.StatusCode;
import org.assessment.constant.StatusMsg;
import org.assessment.domain.reward.PointSummaryDto;
import org.assessment.service.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class RewardControllerTest {

    private MockMvc mockMvc;
    private RewardService rewardService;

    @BeforeEach
    void setUp() {
        rewardService = mock(RewardService.class);
        RewardController rewardController = new RewardController(rewardService);
        mockMvc = standaloneSetup(rewardController).build();
    }

    @Test
    void getAllCustomersPointSummariesTest() throws Exception {
        PointSummaryDto pointSummaryDto = new PointSummaryDto("C001", "John", "Doe", null, 150);
        List<PointSummaryDto> pointSummaries = List.of(pointSummaryDto);

        when(rewardService.getAllPointSummaries(null)).thenReturn(pointSummaries);

        mockMvc.perform(get("/reward/api/v1/pointSummary/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(StatusCode.OK))
                .andExpect(jsonPath("$.msg").value(StatusMsg.NO_ERROR))
                .andExpect(jsonPath("$.data.ent.pointSummaries[0].customerId").value("C001"))
                .andExpect(jsonPath("$.data.ent.pointSummaries[0].totalPoints").value(150));

        verify(rewardService).getAllPointSummaries(null);
    }
}