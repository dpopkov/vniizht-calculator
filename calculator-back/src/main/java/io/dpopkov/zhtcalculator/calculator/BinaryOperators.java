package io.dpopkov.zhtcalculator.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ToDoubleBiFunction;

public class BinaryOperators {

    private static final Map<String, ToDoubleBiFunction<Double, Double>> map;
    static {
        map = new HashMap<>();
        map.put("+", Double::sum);
        map.put("-", (a, b) -> a - b);
        map.put("*", (a, b) -> a * b);
        map.put("/", (a, b) -> a / b);
    }

    public static double calculateFor(String operationSymbol, Double a, Double b) {
        var operation = map.get(operationSymbol);
        if (operation == null) {
            throw new CalculatorException("Unknown operation symbol " + operationSymbol);
        }
        return operation.applyAsDouble(a, b);
    }
}
