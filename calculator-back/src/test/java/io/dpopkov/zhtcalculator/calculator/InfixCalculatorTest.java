package io.dpopkov.zhtcalculator.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InfixCalculatorTest {

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("3 + 2", 5),
                Arguments.of("3.7 + 2.4", 6.1),
                Arguments.of("123.4 * (3.7 + 2.4) - 321.9 / (17.3 + 24.8)", 745.093919239905),
                Arguments.of("sin(-30) * sqrt(144)", -6.0)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testCalculate(String input, double expectedResult) {
        double actual = InfixCalculator.calculate(input);
        assertEquals(expectedResult, actual, 1e-14);
    }
}
