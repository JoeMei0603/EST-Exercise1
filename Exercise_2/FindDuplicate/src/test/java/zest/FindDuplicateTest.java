package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindDuplicateTest {

    @Test
    public void testOnPoint() {
        int[] nums = {1, 2, 2, 3, 4};
        assertEquals(2, FindDuplicate.findDuplicate(nums));
    }

    @Test
    public void testOffPoint() {
        int[] nums = {1, 3, 2, 2, 4};
        assertEquals(2, FindDuplicate.findDuplicate(nums));
    }

    //Task 3: Testing Contracts
    @Test
    void testValidInput() {
        int[] nums = {1, 3, 4, 2, 2};
        assertEquals(2, FindDuplicate.findDuplicate(nums));
    }

    @Test
    void testNullInput() {
        int[] nums = null;
        assertThrows(IllegalArgumentException.class, () -> {
            FindDuplicate.findDuplicate(nums);
        });
    }

    @Test
    void testInsufficientElements() {
        int[] nums = {1};
        assertThrows(IllegalArgumentException.class, () -> {
            FindDuplicate.findDuplicate(nums);
        });
    }

    @Test
    void testInvalidRange() {
        int[] nums = {1, 2, 3, 4, 6};
        assertThrows(IllegalArgumentException.class, () -> {
            FindDuplicate.findDuplicate(nums);
        });
    }

    @Test
    void testPostCondition() {
        int[] nums = {1, 3, 4, 2, 2};
        assertEquals(2, FindDuplicate.findDuplicate(nums));
    }

    @Test
    void testInvariant() {
        int[] nums = {1, 3, 4, 2, 2};
        int result = FindDuplicate.findDuplicate(nums);
        assertTrue(result >= 1 && result <= nums.length - 1);
    }


    @Test
    void testArrayElementsOutOfRange() {
        int[] nums = {0, 1, 2, 3}; // 0 is out of range
        assertThrows(IllegalArgumentException.class, () -> {
            FindDuplicate.findDuplicate(nums);
        });
    }


}