package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class Frac2DecTest {

    @Test
    void testPositiveFraction() {
        String result = Frac2Dec.fractionToDecimal(1, 2);
        assertEquals("0.5", result);
    }

    @Test
    void testNegativeNumerator() {
        String result = Frac2Dec.fractionToDecimal(-1, 8);
        assertEquals("-0.125", result);
    }

    @Test
    void testNegativeDenominator() {
        String result = Frac2Dec.fractionToDecimal(1, -8);
        assertEquals("-0.125", result);
    }

    @Test
    void testNegativeDenominatorAndNumerator() {
        String result = Frac2Dec.fractionToDecimal(-1, -8);
        assertEquals("0.125", result);
    }

    @Test
    void testFractionEqualToInteger() {
        String result = Frac2Dec.fractionToDecimal(4, 1);
        assertEquals("4", result);
    }

    @Test
    void testRepeatingFraction() {
        String result = Frac2Dec.fractionToDecimal(4, 333);
        assertEquals("0.(012)", result);
    }

    @Test
    void testNonRepeatingFraction() {
        String result = Frac2Dec.fractionToDecimal(1, 4);
        assertEquals("0.25", result);
    }

    @Test
    void testNominatorZero() {
        String result = Frac2Dec.fractionToDecimal(0, 5);
        assertEquals("0", result);
    }

    @Test
    void testDenominatorZero() {
        String result = Frac2Dec.fractionToDecimal(5, 0);
        assertNull(result);
    } // modified in Code

    @Test
    void testMultipleAnswersOutcome() {
        String result = Frac2Dec.fractionToDecimal(1, 3);
        assertTrue(result.equals("0.(3)") || result.equals("0.3(3)"));
    }

    @Test
    void testAnswerStringLengthExceeds104() {
        int denominator = 123456789;
        int numerator = 1;
        assertThrows(IllegalArgumentException.class, () -> Frac2Dec.fractionToDecimal(numerator, denominator));
    }// modified in Code


}