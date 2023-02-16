package io.dpopkov.zhtcalculator.calculator;

public class Token {

    private final String value;
    private final TokenType type;
    private int precedenceOrder;

    public Token(String value, TokenType type) {
        this.value = value;
        this.type = type;
        setPrecedenceOrder(type);
    }

    /* The values for precedence order are taken from Java Operator Precedence Table for simplicity. */
    private void setPrecedenceOrder(TokenType type) {
        switch (type) {
            case OPENING_BRACE:
            case CLOSING_BRACE:
                precedenceOrder = 15;
                break;
            case UNARY_OPERATOR:
            case FUNCTION:
                precedenceOrder = 13;
                break;
            case BINARY_OPERATOR:
                if (value.equals("*") || value.equals("/")) {
                    precedenceOrder = 12;
                } else if (value.equals("+") || value.equals("-")) {
                    precedenceOrder = 11;
                } else {
                    throw new CalculatorException("Cannot set precedence for token " + this);
                }
                break;
            case NUMBER:
                precedenceOrder = -1;
                break;
            default:
                throw new CalculatorException("Cannot set precedence for type " + type);
        }
    }

    public Token(TokenType type) {
        this("", type);
    }

    public String getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }

    public int precedence() {
        return precedenceOrder;
    }

    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                ", type=" + type +
                ", precedenceOrder=" + precedenceOrder +
                '}';
    }
}
