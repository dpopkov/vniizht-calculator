package io.dpopkov.zhtcalculator.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BracketCheckerTest {

    @Test
    void check() {
        BracketChecker checker = new BracketChecker();

        assertFalse(checker.check("("));
        assertFalse(checker.check(")"));
        assertFalse(checker.check("(()"));
        assertFalse(checker.check(")((ab))"));
        assertFalse(checker.check("(())("));

        assertTrue(checker.check("(())"));
        assertTrue(checker.check("(())()"));
        assertTrue(checker.check("((ab))(c)"));
    }
}
