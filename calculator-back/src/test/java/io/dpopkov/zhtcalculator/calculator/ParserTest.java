package io.dpopkov.zhtcalculator.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void canParseBracedExpression() {
        Parser parser = new Parser("2 + (-3.1 - 4)");
        assertToken(parser.nextToken(), "2", TokenType.NUMBER);
        assertToken(parser.nextToken(), "+", TokenType.BINARY_OPERATOR);
        assertToken(parser.nextToken(), TokenType.OPENING_BRACE);
        assertToken(parser.nextToken(), "-", TokenType.UNARY_OPERATOR);
        assertToken(parser.nextToken(), "3.1", TokenType.NUMBER);
        assertToken(parser.nextToken(), "-", TokenType.BINARY_OPERATOR);
        assertToken(parser.nextToken(), "4", TokenType.NUMBER);
        assertToken(parser.nextToken(), TokenType.CLOSING_BRACE);
        assertNull(parser.nextToken());
    }

    @Test
    void canParseUnaryMinus() {
        Parser parser = new Parser("(-3*-2) + (-5-2)");
        assertToken(parser.nextToken(), TokenType.OPENING_BRACE);
        assertToken(parser.nextToken(), "-", TokenType.UNARY_OPERATOR);
        assertToken(parser.nextToken(), "3", TokenType.NUMBER);
        assertToken(parser.nextToken(), "*", TokenType.BINARY_OPERATOR);
        assertToken(parser.nextToken(), "-", TokenType.UNARY_OPERATOR);
        assertToken(parser.nextToken(), "2", TokenType.NUMBER);
        assertToken(parser.nextToken(), TokenType.CLOSING_BRACE);
        assertToken(parser.nextToken(), "+", TokenType.BINARY_OPERATOR);
        assertToken(parser.nextToken(), TokenType.OPENING_BRACE);
        assertToken(parser.nextToken(), "-", TokenType.UNARY_OPERATOR);
        assertToken(parser.nextToken(), "5", TokenType.NUMBER);
        assertToken(parser.nextToken(), "-", TokenType.BINARY_OPERATOR);
        assertToken(parser.nextToken(), "2", TokenType.NUMBER);
        assertToken(parser.nextToken(), TokenType.CLOSING_BRACE);
        assertNull(parser.nextToken());
    }

    @Test
    void canParseFunction() {
        Parser parser = new Parser("sin(30)");
        assertToken(parser.nextToken(), "sin", TokenType.FUNCTION);
        assertToken(parser.nextToken(), TokenType.OPENING_BRACE);
        assertToken(parser.nextToken(), "30", TokenType.NUMBER);
        assertToken(parser.nextToken(), TokenType.CLOSING_BRACE);
        assertNull(parser.nextToken());
    }

    private void assertToken(Token token, String expectedValue, TokenType expectedType) {
        assertNotNull(token);
        assertEquals(expectedValue, token.getValue());
        assertEquals(expectedType, token.getType());
    }

    private void assertToken(Token token, TokenType expectedType) {
        assertNotNull(token);
        assertEquals(expectedType, token.getType());
    }
}
