package io.dpopkov.zhtcalculator.controllers;

import io.dpopkov.zhtcalculator.services.CalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/calculate")
public class CalculatorController {

    private final CalculatorService calculatorService;

    @PostMapping
    public ResponseEntity<Double> calculate(@RequestBody CalculationRequest request) {
        log.debug("Processing expression: {}", request.getExpression());
        return ResponseEntity.ok(calculatorService.calculate(request.getExpression()));
    }
}
