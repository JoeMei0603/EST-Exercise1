package zest;

import net.jqwik.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class LongestIncreasingSubsequenceTest {

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        int expected = 0;
        assertEquals(expected, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    public void testNullInput() {
        int[] nums = null;
        assertThrows(IllegalArgumentException.class, () -> {
            new LongestIncreasingSubsequence().lengthOfLIS(nums);
        });
    }

    @Test
    public void testExample2RandomInput() {
        int[] nums = {0, 1, 0, 3, 2, 3};
        int expected = 4;
        assertEquals(expected, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    public void testAllElementsSame() {
        int[] nums = {7, 7, 7, 7, 7, 7, 7};
        int expected = 1;
        assertEquals(expected, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    //Task 3: Testing Contracts
    @Test
    public void testNegativeNumbers() {
        int[] nums = {-5, -2, -3, -1, 0};
        int expected = 4;
        assertEquals(expected, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    public void testIncreasingSequence() {
        int[] nums = {1, 2, 3, 4, 5};
        int expected = 5;
        assertEquals(expected, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    public void testDecreasingSequence() {
        int[] nums = {5, 4, 3, 2, 1};
        int expected = 1;
        assertEquals(expected, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    public void testRandomSequence() {
        int[] nums = {5, 3, 2, 4, 8, 6, 7};
        int expected = 4;
        assertEquals(expected, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    public void testSingleElement() {
        int[] nums = {42};
        int expected = 1;
        assertEquals(expected, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    //Task 4

}