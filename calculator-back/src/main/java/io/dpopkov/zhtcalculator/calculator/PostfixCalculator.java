package io.dpopkov.zhtcalculator.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class PostfixCalculator {

    public static double calculate(Deque<Token> postfix) {
        Deque<Double> stack = new ArrayDeque<>();
        while (!postfix.isEmpty()) {
            Token token = postfix.pollFirst();
            if (token.getType() == TokenType.NUMBER) {
                try {
                    stack.push(Double.parseDouble(token.getValue()));
                } catch (NumberFormatException ex) {
                    throw new CalculatorException("Incorrect formatted number: " + token.getValue(), ex);
                }
            } else {
                Double operand = stack.pop();
                TokenType type = token.getType();
                String symbol = token.getValue();
                if (type == TokenType.UNARY_OPERATOR) {
                    stack.push(UnaryOperators.calculateFor(symbol, operand));
                }
                else if (type == TokenType.FUNCTION) {
                    stack.push(Functions.calculateForName(symbol, operand));
                }
                else if (type == TokenType.BINARY_OPERATOR) {
                    Double leftOperand = stack.pop();
                    stack.push(BinaryOperators.calculateFor(symbol, leftOperand, operand));
                }
            }
        }
        return stack.pop();
    }
}
