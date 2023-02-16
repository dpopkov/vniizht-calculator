package io.dpopkov.zhtcalculator.calculator;

import java.util.Deque;
import java.util.Scanner;

public class InfixCalculator {

    /**
     * Вычисляет выражение содержащее арифметические операции в инфиксной нотации.
     * Операнды должны быть представлены числами, где в качестве разделителя между
     * целой и дробной частью используется точка.
     * @param input строка содержащая выражение
     * @return результат вычисления
     */
    public static double calculate(String input) {
        Deque<Token> postfix = new InfixTranslator().toPostfix(input);
        return PostfixCalculator.calculate(postfix);
    }

    public static void main(String[] args) {
        System.out.println("Manual testing of Infix Calculator.");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter expression: ");
        String input = scanner.nextLine();
        double result = calculate(input);
        System.out.println("result = " + result);
    }
}
