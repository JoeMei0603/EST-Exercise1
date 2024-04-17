package zest;

public class ClimbingStairs {
    public static long climbStairs(int n) {
        if (n < 0) throw new IllegalArgumentException("Cannot climb a negative staircase!");
        if (n > 91) throw new IllegalArgumentException("Cannot climb a staircase with more than 91 steps!");

        if (n <= 2) {
            return n;
        }
        long oneStepBefore = 2;
        long twoStepsBefore = 1;
        long allWays = 0;

        for (int i = 2; i < n; i++) {
            allWays = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = allWays;

            if (allWays < 0) throw new RuntimeException("Cannot climb a staircase with a negative combination!");
        }
        return allWays;
    }
}
