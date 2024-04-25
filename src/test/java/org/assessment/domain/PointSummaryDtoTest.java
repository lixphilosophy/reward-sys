package org.assessment.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class PointSummaryDtoTest {

    @Test
    public void testGettersAndSetters() {
        PointSummaryDto pointSummary = new PointSummaryDto();

        pointSummary.setCustomerId("C001");
        assertEquals("C001", pointSummary.getCustomerId());

        pointSummary.setCustomerFirstName("John");
        assertEquals("John", pointSummary.getCustomerFirstName());

        pointSummary.setCustomerLastName("Doe");
        assertEquals("Doe", pointSummary.getCustomerLastName());

        Map<String, Integer> pointsMap = new HashMap<>();
        pointsMap.put("2024-04", 100);
        pointSummary.setPointsPerMonth(pointsMap);
        assertEquals(pointsMap, pointSummary.getPointsPerMonth());
        assertEquals(100, pointSummary.getPointsPerMonth().get("2024-04"));

        pointSummary.setTotalPoints(250);
        assertEquals(250, pointSummary.getTotalPoints());
    }

    @Test
    public void testPointSummaryDtoBuilder() {
        Map<String, Integer> pointsPerMonth = new HashMap<>();
        pointsPerMonth.put("2024-04", 100);
        pointsPerMonth.put("2024-05", 150);

        PointSummaryDto pointSummary = PointSummaryDto.builder()
                .customerId("C001")
                .customerFirstName("John")
                .customerLastName("Doe")
                .pointsPerMonth(pointsPerMonth)
                .totalPoints(250)
                .build();

        assertEquals("C001", pointSummary.getCustomerId());
        assertEquals("John", pointSummary.getCustomerFirstName());
        assertEquals("Doe", pointSummary.getCustomerLastName());
        assertEquals(pointsPerMonth, pointSummary.getPointsPerMonth());
        assertEquals(250, pointSummary.getTotalPoints());
    }
}

