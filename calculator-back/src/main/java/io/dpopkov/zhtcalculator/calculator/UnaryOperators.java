package io.dpopkov.zhtcalculator.calculator;

public class UnaryOperators {

    public static double calculateFor(String operationSymbol, Double input) {
        if ("-".equals(operationSymbol)) {
            return -input;
        }
        throw new CalculatorException("Incorrect Unary operation: " + operationSymbol);
    }
}
