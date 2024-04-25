package org.assessment.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void testCalculator() {
        Calculator calculator = new Calculator();
        assertEquals(calculator.getClass(), Calculator.class);
    }

    @Test
    public void testCalculatePoints_NoPoints() {
        assertEquals(0, Calculator.calculatePoints(50));
        assertEquals(0, Calculator.calculatePoints(25));
        assertEquals(0, Calculator.calculatePoints(0));
    }

    @Test
    public void testCalculatePoints_OnePointPerDollar() {
        assertEquals(1, Calculator.calculatePoints(51));
        assertEquals(25, Calculator.calculatePoints(75));
        assertEquals(49, Calculator.calculatePoints(99));
    }

    @Test
    public void testCalculatePoints_TwoPointsPerDollarOver100() {
        assertEquals(52, Calculator.calculatePoints(101));
        assertEquals(100, Calculator.calculatePoints(125));
        assertEquals(150, Calculator.calculatePoints(150));
    }
}
