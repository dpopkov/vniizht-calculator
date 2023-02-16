package io.dpopkov.zhtcalculator.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.DoubleUnaryOperator;

public class Functions {

    private static final Map<String, DoubleUnaryOperator> map;
    static {
        map = new HashMap<>();
        map.put("sin", degrees -> Math.sin(Math.toRadians(degrees)));
        map.put("sqrt", Math::sqrt);
    }

    public static Set<String> getNames() {
        return map.keySet();
    }

    public static double calculateForName(String functionName, double input) {
        DoubleUnaryOperator function = map.get(functionName);
        if (function == null) {
            throw new CalculatorException("Cannot find function " + functionName);
        }
        return function.applyAsDouble(input);
    }
}
