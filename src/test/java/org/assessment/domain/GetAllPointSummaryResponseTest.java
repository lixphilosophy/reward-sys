package org.assessment.domain;

import org.assessment.domain.response.GetAllPointSummaryResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class GetAllPointSummaryResponseTest {
    @Test
    public void testBuilderAndAccessors() {
        PointSummaryDto summary1 = PointSummaryDto.builder()
                .customerId("C001")
                .customerFirstName("John")
                .customerLastName("Doe")
                .totalPoints(150)
                .build();
        PointSummaryDto summary2 = PointSummaryDto.builder()
                .customerId("C002")
                .customerFirstName("Alice")
                .customerLastName("Smith")
                .totalPoints(250)
                .build();

        List<PointSummaryDto> summaries = Arrays.asList(summary1, summary2);
        GetAllPointSummaryResponse response = GetAllPointSummaryResponse.builder()
                .pointSummaries(summaries)
                .build();

        assertNotNull(response.getPointSummaries());
        assertEquals(2, response.getPointSummaries().size());
        assertSame(summaries, response.getPointSummaries());
    }

    @Test
    public void testSettersAndGetters() {
        GetAllPointSummaryResponse response = new GetAllPointSummaryResponse();
        List<PointSummaryDto> summaries = Arrays.asList(new PointSummaryDto(), new PointSummaryDto());

        response.setPointSummaries(summaries);

        assertNotNull(response.getPointSummaries());
        assertEquals(2, response.getPointSummaries().size());
    }
}
