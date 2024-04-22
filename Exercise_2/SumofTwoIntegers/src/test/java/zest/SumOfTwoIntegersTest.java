package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.LongRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SumOfTwoIntegersTest {
    // 2147483647  -2,147,483,648
    @Test
    void testSumofTwoPostiveIntegers() {
        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();
        long result = testSumClass.getSum(2,3);

        Assertions.assertEquals(5, result);
    }

    @Test
    void testSumofTwoNegativeIntegers() {
        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();
        long result = testSumClass.getSum(-2,-5);

        Assertions.assertEquals(-7, result);
    }

    @Test
    void testSumofPositiveAndZero() {
        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();
        long result = testSumClass.getSum(2,0);

        Assertions.assertEquals(2, result);
    }

    @Test
    void testSumofTwoIntegersResultGreaterThanMaxINT() {
        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();
        long result = testSumClass.getSum(2147483647,3);

        Assertions.assertEquals(2147483650L, result);
    }

    @Test
    void testSumofTwoIntegersPassingAGreaterThanMaxINT() {
        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();

        assertThrows(IllegalArgumentException.class, () -> testSumClass.getSum(2147483648L,6));

    }

    @Test
    void testSumofTwoIntegersPassingBGreaterThanMaxINT() {
        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();

        assertThrows(IllegalArgumentException.class, () -> testSumClass.getSum(1,2147483648L));

    }


    @Property
    void testSumofTwoIntegerProperty(@ForAll @LongRange(max = Integer.MAX_VALUE, min = Integer.MIN_VALUE) long a, @ForAll @LongRange(max = Integer.MAX_VALUE, min = Integer.MIN_VALUE) long b) {

        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();
        long result = testSumClass.getSum(a,b);

        assertEquals(a+b, result);
    }

    @Property
    void testSumofTwoInvalidIntegerProperty(@ForAll("invalidArguments") long a, @ForAll("invalidArguments") long b) {

        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();

        assertThrows(IllegalArgumentException.class, () -> testSumClass.getSum(a,b));

    }

    @Property
    void testSumWithInvalidAProperty(@ForAll("invalidArguments") long a, @ForAll @LongRange(max = Integer.MAX_VALUE, min = Integer.MIN_VALUE) long b) {

        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();

        assertThrows(IllegalArgumentException.class, () -> testSumClass.getSum(a,b));

    }


    @Property
    void testSumWithInvalidBProperty(@ForAll @LongRange(max = Integer.MAX_VALUE, min = Integer.MIN_VALUE) long a, @ForAll("invalidArguments") long b) {

        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();

        assertThrows(IllegalArgumentException.class, () -> testSumClass.getSum(a,b));

    }


    @Provide
    private Arbitrary<Long> invalidArguments() {
        return Arbitraries.oneOf(
                Arbitraries.longs().lessOrEqual(-2147483649L),
                Arbitraries.longs().greaterOrEqual(2147483648L)
        );
    }





}
