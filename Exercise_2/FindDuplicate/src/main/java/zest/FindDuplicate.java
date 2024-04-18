package zest;

public class FindDuplicate {
    public static int findDuplicate(int[] nums) {

        // Preconditions
        if (nums == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        if (nums.length < 2) {
            throw new IllegalArgumentException("Input array must have at least two elements");
        }

        for (int num : nums) {
            if (num < 1 || num > nums.length - 1) {
                throw new IllegalArgumentException("Array elements must be within the range [1, n]");
            }
        }


        int tortoise = nums[0];
        int hare = nums[0];
        // Phase 1: Finding the intersection point of the two runners.
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Phase 2: Finding the "entrance" to the cycle.
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        // Post-condition: The method returns an integer representing the duplicate number found in the array nums.
        int duplicateNumber = hare;
        if (duplicateNumber < 1 || duplicateNumber > nums.length - 1) {
            throw new IllegalStateException("Invalid duplicate number found: " + duplicateNumber);
        }


        return duplicateNumber;
    }


}
