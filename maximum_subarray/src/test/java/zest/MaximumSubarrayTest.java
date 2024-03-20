package zest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaximumSubarrayTest {

    @Test
    void testEmptyArray() {
        int result = MaximumSubarray.maxSubArray(new int[0]);

        Assertions.assertEquals(0, result);
    }

    @Test
    void testArrayOneElement() {
        int resultFromArraySinglePositive = MaximumSubarray.maxSubArray(new int[]{1});
        int resultFromArraySingleNegative = MaximumSubarray.maxSubArray(new int[]{-1});

        Assertions.assertEquals(1, resultFromArraySinglePositive);
        Assertions.assertEquals(-1, resultFromArraySingleNegative);
    }

    @Test
    void testArrayMultipleElements() {
        int resultFromArrayMultiplePositives = MaximumSubarray.maxSubArray(new int[]{1, 2, 3, 4});
        int resultFromArrayMultipleNegatives = MaximumSubarray.maxSubArray(new int[]{-1, -2, -3, -4});

        Assertions.assertEquals(10, resultFromArrayMultiplePositives);
        Assertions.assertEquals(-1, resultFromArrayMultipleNegatives);
    }

    @Test
    void testArrayMultipleElementsMixed() {
        int resultFromArrayMultipleMixed = MaximumSubarray.maxSubArray(new int[]{-1, 2, -3, 4});
        int resultFromArrayMultipleReversedMixed = MaximumSubarray.maxSubArray(new int[]{-4, 3, -2, 1});

        Assertions.assertEquals(4, resultFromArrayMultipleMixed);
        Assertions.assertEquals(3, resultFromArrayMultipleReversedMixed);
    }

    @Test
    void testArrayMultipleElementsMixedReversedSign() {
        int resultFromArrayMultipleMixedReversedSign = MaximumSubarray.maxSubArray(new int[]{1, -2, 3, -4});
        int resultFromArrayMultipleReversedMixedReversedSign = MaximumSubarray.maxSubArray(new int[]{4, -3, 2, -1});

        Assertions.assertEquals(3, resultFromArrayMultipleMixedReversedSign);
        Assertions.assertEquals(4, resultFromArrayMultipleReversedMixedReversedSign);
    }
}