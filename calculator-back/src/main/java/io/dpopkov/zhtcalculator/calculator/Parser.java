package io.dpopkov.zhtcalculator.calculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final String FLOATING_POINT_NUMBER = "([0-9]*[.])?[0-9]+";
    private final String input;
    private final Matcher numberMatcher;
    private final FunctionMatcher functionMatcher;
    private int currentPosition;
    private TokenType previous;

    public Parser(String input) {
        this.input = input;
        currentPosition = 0;
        numberMatcher = Pattern.compile(FLOATING_POINT_NUMBER).matcher(input);
        functionMatcher = new FunctionMatcher(Functions.getNames());
    }

    public Token nextToken() {
        eatSpaces();
        if (currentPosition >= input.length()) {
            return null;
        }
        String functionName;
        char ch = input.charAt(currentPosition);
        if (ch == '(') {
            currentPosition++;
            return newToken(TokenType.OPENING_BRACE);
        } else if (ch == ')') {
            currentPosition++;
            return newToken(TokenType.CLOSING_BRACE);
        } else if (ch == '-') {
            currentPosition++;
            if (previous == null || previous == TokenType.BINARY_OPERATOR || previous == TokenType.OPENING_BRACE) {
                return newToken("-", TokenType.UNARY_OPERATOR);
            } else if (previous == TokenType.NUMBER || previous == TokenType.CLOSING_BRACE) {
                return newToken("-", TokenType.BINARY_OPERATOR);
            } else {
                throw new CalculatorException("Non-identifiable 'Minus' at position " + (currentPosition - 1));
            }
        } else if (ch == '+' || ch == '*' || ch == '/') {
            currentPosition++;
            return newToken(Character.toString(ch), TokenType.BINARY_OPERATOR);
        } else if ((functionName = functionMatcher.hasFound()) != null) {
            currentPosition += functionName.length();
            return newToken(functionName, TokenType.FUNCTION);
        } else if (numberMatcher.find(currentPosition)) {
            String value = numberMatcher.group();
            currentPosition += value.length();
            return newToken(value, TokenType.NUMBER);
        }
        return null;
    }

    private Token newToken(String value, TokenType type) {
        return new Token(value, previous = type);
    }

    private Token newToken(TokenType type) {
        return new Token(previous = type);
    }

    private void eatSpaces() {
        while (currentPosition < input.length()
                && Character.isWhitespace(input.charAt(currentPosition))) {
            currentPosition++;
        }
    }

    private class FunctionMatcher {
        private final List<String> names;

        private FunctionMatcher(Collection<String> names) {
            this.names = new ArrayList<>(names);
        }

        String hasFound() {
            for (String name : names) {
                if (input.startsWith(name, currentPosition)) {
                    return name;
                }
            }
            return null;
        }
    }
}
