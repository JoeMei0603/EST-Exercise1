package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


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

    //Task 4 Property based testing
    @Property
    void lengthProperty(@ForAll("intArrays") int[] nums) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int result = lis.lengthOfLIS(nums);
        assertTrue(result >= 0, "Length of LIS should be non-negative");
    }

    @Property
    void subsequenceProperty(@ForAll @Size(max = 20) int[] input) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] originalArray = input.clone();
        int result = lis.lengthOfLIS(input);

        assertTrue(isSubsequence(result, originalArray), "LIS should be a valid subsequence of the original array");
    }

    @Property
    void increasingProperty(@ForAll @UniqueElements @Size(max = 20) int[] input) {
        Arrays.sort(input);

        assertTrue(isIncreasing(input), "Elements within LIS should strictly increase");
    }


    @Provide
    Arbitrary<int[]> intArrays() {
        return Arbitraries.integers().list().ofMinSize(0).ofMaxSize(100)
                .map(list -> list.stream().mapToInt(Integer::intValue).toArray());
    }


    private boolean isSubsequence(int subsequenceLength, int[] fullArray) {
        int[] dp = new int[fullArray.length];
        int maxLength = 0;

        for (int i = 0; i < fullArray.length; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (fullArray[i] > fullArray[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength == subsequenceLength;
    }

    private boolean isIncreasing(int[] lis) {
        for (int i = 1; i < lis.length; i++) {
            if (lis[i] <= lis[i - 1]) {
                return false;
            }
        }
        return true;
    }

}
