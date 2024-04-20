package zest;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SumOfTwoIntegersTest {
    @Test
    void testSumofTwoPostiveIntegers() {
        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();
        int result = testSumClass.getSum(2,3);

        Assertions.assertEquals(5, result);
    }

    @Test
    void testSumofTwoNegativeIntegers() {
        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();
        int result = testSumClass.getSum(-2,-5);

        Assertions.assertEquals(-7, result);
    }

    @Test
    void testSumofPositiveAndZero() {
        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();
        int result = testSumClass.getSum(2,0);

        Assertions.assertEquals(2, result);
    }


    @Property
    void testSumofTwoIntegerProperty(@ForAll @IntRange(max = Integer.MAX_VALUE, min = Integer.MIN_VALUE) int a,@ForAll @IntRange(max = Integer.MAX_VALUE, min = Integer.MIN_VALUE) int b) {

        SumOfTwoIntegers testSumClass = new SumOfTwoIntegers();
        int result = testSumClass.getSum(a,b);

        assertEquals(a+b, result);
    }



}
