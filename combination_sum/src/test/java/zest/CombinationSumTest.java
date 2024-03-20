package zest;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CombinationSumTest {

    @Test
    void testNegativeTarget() {
        int[] candidates = {1, 2, 3, 4};
        int target = -6;
        List<List<Integer>> expected = new ArrayList<>();
        assertTrue(CombinationSum.combinationSum(candidates, target).isEmpty(), "No combination is possible with positive candidates and a negative target");
    }

    @Test
    void testTargetInList() {
        int[] candidates = {3, 6, 7};
        int target = 7;
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(7));
        assertEquals(expected, CombinationSum.combinationSum(candidates, target));
    }

    @Test
    void testTargetIsSumOfElements() {
        int[] candidates = {2, 3, 5};
        int target = 8;
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(2, 2, 2, 2));
        expected.add(Arrays.asList(2, 3, 3));
        expected.add(Arrays.asList(3, 5));
        assertEquals(expected, CombinationSum.combinationSum(candidates, target));
    }

    @Test
    void testTargetSmallerThanSmallestElementInList() {
        int[] candidates = {3, 5, 7};
        int target = 1;
        List<List<Integer>> expected = new ArrayList<>();
        assertEquals(expected, CombinationSum.combinationSum(candidates, target));
    }


    @Test
    void testEmptyArray() {
        int[] candidates = {};
        int target = 2;
        List<List<Integer>> combinations = CombinationSum.combinationSum(candidates, target);
        assertTrue(combinations.isEmpty(), "Combinations should be empty for an empty array of candidates");
    }


    @Test
    void testNegativeCandidatesWithPositiveTarget() {
        int[] candidates = {-1, -2, -3};
        int target = 4;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CombinationSum.combinationSum(candidates, target);
        });
        String expectedErrorMessage = "Negative candidates are not allowed.";
        assertTrue(exception.getMessage().contains(expectedErrorMessage),
                "Exception message should indicate that negative candidates are not allowed");
    } // the code has been modified for this part --> assumptions only positive candidates allowed


    @Test
    void testNonDistinctCandidates() {
        int[] candidates = {1, 1, 2, 2};
        int target = 4;

        // Use assertThrows to check if IllegalArgumentException is thrown
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CombinationSum.combinationSum(candidates, target);
        });

        // Assert that the exception message contains the expected error message
        String expectedErrorMessage = "Duplicate candidates found: 1. Duplicate candidates are not allowed.";
        assertTrue(exception.getMessage().contains(expectedErrorMessage),
                "Exception message should contain information about duplicate candidates");
    } // the code has been modified for this part!

    @Test
    void testMoreThan150Combinations() {
        int[] candidates = {1, 2};
        int target = 300;

        // Use assertThrows to check if IllegalArgumentException is thrown
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CombinationSum.combinationSum(candidates, target);
        });

        // Assert that the exception message contains the expected error message
        String expectedErrorMessage = "Number of unique combinations that sum up to target exceeds 150. This is not allowed.";
        assertTrue(exception.getMessage().contains(expectedErrorMessage),
                "Exception message should indicate that number of combinations exceeds 150");
    } // The Code has been modified for this part!

    @Test
    void testNoCombinationPossible() {
        int[] candidates = {2, 4};
        int target = 13;
        List<List<Integer>> expected = new ArrayList<>();
        assertEquals(expected, CombinationSum.combinationSum(candidates, target));
    }

    //After MutationTesting implemented
    @Test
    public void testNegativeCandidate() {
        try {
            int[] candidates = {0, 1, 2, -3, 4};
            int target = 10;
            CombinationSum.combinationSum(candidates, target);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Negative candidates are not allowed.", e.getMessage());
        }
    }

    @Test
    public void testNoNegativeCandidate() {
        int[] candidates = {1, 2, 3, 4};
        int target = 10;
        List<List<Integer>> result = CombinationSum.combinationSum(candidates, target);
        assertNotNull(result);

    }

    @Test
    void testCandidateAndTargetEqualZero() {
        int[] candidates = {0};
        int target = 0;
        List<List<Integer>> expected = List.of(List.of());
        assertEquals(expected, CombinationSum.combinationSum(candidates, target));
    }


}