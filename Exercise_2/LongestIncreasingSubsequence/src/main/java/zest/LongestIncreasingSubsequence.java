package zest;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {

        // Preconditions
        if (nums == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLength = Math.max(maxLength, dp[i]);
        }

        // Post-condition (in this case always true...)
        assert maxLength >= 0 : "Length of longest increasing subsequence must be >= 0";

        return maxLength;
    }
}

