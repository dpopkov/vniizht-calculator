package io.dpopkov.zhtcalculator.services;

import io.dpopkov.zhtcalculator.calculator.InfixCalculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public Double calculate(String expression) {
        return InfixCalculator.calculate(properFormat(expression));
    }

    private String properFormat(String expression) {
        return expression.toLowerCase().replaceAll(",", ".");
    }
}
