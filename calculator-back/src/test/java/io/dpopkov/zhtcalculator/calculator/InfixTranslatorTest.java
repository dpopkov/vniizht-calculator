package io.dpopkov.zhtcalculator.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Deque;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InfixTranslatorTest {
    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("3 + 2", "32+"),
                Arguments.of("3 + 2 * 5", "325*+"),
                Arguments.of("1 * (2 + 3) - 4 / (5 + 6)", "123+*456+/-"),
                Arguments.of("2 * (3 + 4) - 5 / (7 + 8)", "234+*578+/-"),
                Arguments.of("3 + sin(30)", "330sin+"),
                Arguments.of("sqrt(3) / sin(30)", "3sqrt30sin/")
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void toPostfix(String infix, String expectedPostfix) {
        Deque<Token> postfix = new InfixTranslator().toPostfix(infix);
        StringBuilder sb = new StringBuilder();
        while (!postfix.isEmpty()) {
            sb.append(postfix.pollFirst().getValue());
        }
        assertEquals(expectedPostfix, sb.toString());
    }
}
