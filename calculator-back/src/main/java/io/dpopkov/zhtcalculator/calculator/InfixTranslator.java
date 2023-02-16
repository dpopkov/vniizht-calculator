package io.dpopkov.zhtcalculator.calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class InfixTranslator {
    private final Deque<Token> postfix = new ArrayDeque<>();
    private final Deque<Token> stack = new ArrayDeque<>();

    public Deque<Token> toPostfix(String infix) {
        Parser parser = new Parser(infix);
        Token token;
        while ((token = parser.nextToken()) != null) {
            switch (token.getType()) {
                case OPENING_BRACE:
                case UNARY_OPERATOR:
                case FUNCTION:
                    stack.push(token);
                    break;
                case BINARY_OPERATOR:
                    processBinary(token);
                    break;
                case CLOSING_BRACE:
                    processClosing();
                    break;
                default:
                    postfix.addLast(token);
                    break;
            }
        }
        while (!stack.isEmpty()) {
            postfix.addLast(stack.pop());
        }
        return postfix;
    }

    private void processBinary(Token operator) {
        while (!stack.isEmpty()) {
            Token opTop = stack.pop();
            if (opTop.getType() == TokenType.OPENING_BRACE) {
                stack.push(opTop);
                break;
            } else {
                if (opTop.precedence() < operator.precedence()) {
                    stack.push(opTop);
                    break;
                } else {
                    postfix.addLast(opTop);
                }
            }
        }
        stack.push(operator);
    }

    private void processClosing() {
        while (!stack.isEmpty()) {
            Token t = stack.pop();
            if (t.getType() == TokenType.OPENING_BRACE) {
                break;
            } else {
                postfix.addLast(t);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Test Infix fo Postfix Translation Manually.");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter infix expression: ");
        String input = sc.nextLine();
        Deque<Token> postfix = new InfixTranslator().toPostfix(input);
        while (!postfix.isEmpty()) {
            System.out.println(postfix.pollFirst());
        }
    }
}
