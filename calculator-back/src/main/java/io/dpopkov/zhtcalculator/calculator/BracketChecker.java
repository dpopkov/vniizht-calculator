package io.dpopkov.zhtcalculator.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class BracketChecker {

    public boolean check(String input) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            switch (ch) {
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    } else if (stack.pop() != '(') {
                        return false;
                    }
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }
}
