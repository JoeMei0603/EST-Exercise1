package zest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GenerateParenthesesTest {

    @Test
    void testInputZero() {
        List<String> result = GenerateParentheses.generateParentheses(0);
        List<String> expectedCombinations = Collections.emptyList();

        Assertions.assertEquals(expectedCombinations, result);
    }

    @Test
    void testInputOne() {
        List<String> result = GenerateParentheses.generateParentheses(1);
        List<String> expectedCombinations = List.of("()");

        Assertions.assertEquals(expectedCombinations, result);
    }

    @Test
    void testInputThree() {
        List<String> result = GenerateParentheses.generateParentheses(3);
        List<String> expectedCombinations = List.of("((()))","(()())","(())()","()(())","()()()");

        Assertions.assertEquals(expectedCombinations, result);
        Assertions.assertEquals(5, result.size());
    }

    @Test
    void testInputEight() {
        List<String> result = GenerateParentheses.generateParentheses(8);

        Assertions.assertEquals(1430, result.size());
    }

    @Test
    void testInputNine() {
        assertThrows(IllegalArgumentException.class, () -> GenerateParentheses.generateParentheses(9));
    }
}