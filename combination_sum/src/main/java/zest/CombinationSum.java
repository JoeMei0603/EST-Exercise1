package zest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Check if any candidate is negative
        for (int candidate : candidates) {
            if (candidate < 0) {
                throw new IllegalArgumentException("Negative candidates are not allowed.");
            }
        }

        // Check for duplicate candidates
        Arrays.sort(candidates);
        for (int i = 1; i < candidates.length; i++) {
            if (candidates[i] == candidates[i - 1]) {
                throw new IllegalArgumentException("Duplicate candidates found: " + candidates[i] + ". Duplicate candidates are not allowed.");
            }
        }

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getResult(result, new ArrayList<Integer>(), candidates, target, 0);

        // Check if the size of the result list is less than 150
        if (result.size() >= 150) {
            throw new IllegalArgumentException("Number of unique combinations that sum up to target exceeds 150. This is not allowed.");
        }

        return result;
    }

    private static void getResult(List<List<Integer>> result, List<Integer> cur, int[] candidates, int target, int start) {
        if (target > 0) {
            for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
                cur.add(candidates[i]);
                getResult(result, cur, candidates, target - candidates[i], i);
                cur.remove(cur.size() - 1);
            }
        } else if (target == 0) {
            result.add(new ArrayList<Integer>(cur));
        }
    }
}
