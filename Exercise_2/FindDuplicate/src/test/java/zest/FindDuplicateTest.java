package zest;

import net.jqwik.api.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static zest.FindDuplicate.findDuplicate;

class FindDuplicateTest {

    @Test
    public void testOnPoint() {
        int[] nums = {1, 2, 2, 3, 4};
        assertEquals(2, findDuplicate(nums));
    }

    @Test
    public void testOffPoint() {
        int[] nums = {1, 3, 2, 2, 4};
        assertEquals(2, findDuplicate(nums));
    }

    //Task 3: Testing Contracts
    @Test
    void testValidInput() {
        int[] nums = {1, 3, 4, 2, 2};
        assertEquals(2, findDuplicate(nums));
    }

    @Test
    void testNullInput() {
        int[] nums = null;
        assertThrows(IllegalArgumentException.class, () -> {
            findDuplicate(nums);
        });
    }

    @Test
    void testInsufficientElements() {
        int[] nums = {1};
        assertThrows(IllegalArgumentException.class, () -> {
            findDuplicate(nums);
        });
    }

    @Test
    void testInvalidRange() {
        int[] nums = {1, 2, 3, 4, 6};
        assertThrows(IllegalArgumentException.class, () -> {
            findDuplicate(nums);
        });
    }

    @Test
    void testPostCondition() {
        int[] nums = {1, 3, 4, 2, 2};
        assertEquals(2, findDuplicate(nums));
    }

    @Test
    void testInvariant() {
        int[] nums = {1, 3, 4, 2, 2};
        int result = findDuplicate(nums);
        assertTrue(result >= 1 && result <= nums.length - 1);
    }


    @Test
    void testArrayElementsOutOfRange() {
        int[] nums = {0, 1, 2, 2}; // 0 is out of range
        assertThrows(IllegalArgumentException.class, () -> {
            findDuplicate(nums);
        });
    }

    //Task 4 Property Testing
    @Property
    void existenceOfDuplicate(@ForAll("validArrays") int[] nums) {
        int duplicate = FindDuplicate.findDuplicate(nums);
        boolean duplicateFound = false;
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                duplicateFound = true;
                break;
            }
        }
        assertTrue(duplicateFound);
        assertTrue(duplicate >= 1 && duplicate <= nums.length - 1);
    }

    @Property
    void correctness(@ForAll("validArrays") int[] nums) {
        int duplicate = FindDuplicate.findDuplicate(nums);
        assertTrue(Arrays.stream(nums).filter(num -> num == duplicate).count() > 1);
    }

    @Property
    void withinRange(@ForAll("validArrays") int[] nums) {
        int duplicate = FindDuplicate.findDuplicate(nums);
        assertTrue(duplicate >= 1 && duplicate <= nums.length - 1);
    }

    @Provide
    Arbitrary<int[]> validArrays() {
        return Arbitraries.integers().between(1, 100)
                .flatMap(size -> {
                    List<Integer> list = new ArrayList<>();
                    for (int i = 1; i <= size; i++) {
                        list.add(i);
                    }
                    Collections.shuffle(list);
                    list.add(list.get(size - 1)); // Add duplicate at the end
                    return Arbitraries.just(list.stream().mapToInt(Integer::intValue).toArray());
                });
    }

}