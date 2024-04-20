package zest;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SortedArrayToBSTTest {

    private static SortedArrayToBST classInstance;

    @BeforeAll
    static void setupClass() {
        classInstance = new SortedArrayToBST();
    }

    @Test
    void testEmptyArray() {
        int[] nums = {};
        TreeNode treeResult = classInstance.sortedArrayToBST(nums);
        List<Integer> listResult = classInstance.levelOrderTraversal(treeResult);

        Assertions.assertNull(treeResult);
        Assertions.assertEquals(listResult.size(), 0);
    }

    @Test
    void testArrayOne() {
        int[] nums = {1};
        TreeNode treeResult = classInstance.sortedArrayToBST(nums);
        List<Integer> listResult = classInstance.levelOrderTraversal(treeResult);

        Assertions.assertEquals(listResult.size(), 1);
        Assertions.assertEquals(listResult.get(0), 1);
    }

    @Test
    void testSimpleArray() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        TreeNode treeResult = classInstance.sortedArrayToBST(nums);
        List<Integer> listResult = classInstance.levelOrderTraversal(treeResult);

        int root = listResult.get(0);

        Assertions.assertEquals(root, 4);
    }

    @Test
    void testArrayNull() {
        int[] nums = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> classInstance.sortedArrayToBST(nums));
    }

    @Test
    void testArrayTooLong() {
        int[] nums = createTooLargeArray();

        Assertions.assertThrows(IllegalArgumentException.class, () -> classInstance.sortedArrayToBST(nums));
    }

    @Test
    void testArrayLengthBoundary() {
        int[] nums = createLargeArray();
        TreeNode treeResult = classInstance.sortedArrayToBST(nums);
        List<Integer> listResult = classInstance.levelOrderTraversal(treeResult);

        Assertions.assertEquals(listResult.size(), 10000);
    }

    @Test
    void testNonSortedArray() {
        int[] nums = {1, 2, 3, 4, 5, 7, 6};

        Assertions.assertThrows(IllegalArgumentException.class, () -> classInstance.sortedArrayToBST(nums));
    }

    @Test
    void testNonUniqueArray() {
        int[] nums = {1, 2, 3, 3, 4, 5};

        Assertions.assertThrows(IllegalArgumentException.class, () -> classInstance.sortedArrayToBST(nums));
    }

    @Test
    void testNonUniqueArrayAtEnd() {
        int[] nums = {1, 2, 3, 4, 5, 6, 6};

        Assertions.assertThrows(IllegalArgumentException.class, () -> classInstance.sortedArrayToBST(nums));
    }

    private static int[] createTooLargeArray() {
        int[] nums = new int[10001];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        return nums;
    }

    private static int[] createLargeArray() {
        int[] nums = new int[10000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        return nums;
    }

    @Property
    void testRandomValidIntegers(@ForAll("createListOfValidIntegers") int[] nums) {
        SortedArrayToBST classInstance = new SortedArrayToBST();
        TreeNode treeResult = classInstance.sortedArrayToBST(nums);
        int numsLength = nums.length;
        List<Integer> listResult = classInstance.levelOrderTraversal(treeResult);

        Assertions.assertEquals(numsLength, listResult.size());
    }

    @Property(tries = 10)
    void testInvalidArrayLengths(@ForAll("createInvalidArrayLength") int[] nums) {
        SortedArrayToBST classInstance = new SortedArrayToBST();

        Assertions.assertThrows(IllegalArgumentException.class, () -> classInstance.sortedArrayToBST(nums));
    }

    @Provide
    Arbitrary<int[]> createListOfValidIntegers() {
        return Arbitraries.integers().array(int[].class).ofMinSize(0).ofMaxSize(10000)
                .map(integers -> {
                    Set<Integer> uniqueSet = new HashSet<>();
                    for (int i: integers){
                        uniqueSet.add(i);
                    }
                    int[] uniqueArray = uniqueSet.stream().mapToInt(Integer::intValue).toArray();
                    Arrays.sort(uniqueArray);
                    return uniqueArray;
                });
    }

    @Provide
    Arbitrary<int[]> createInvalidArrayLength(){
        return Arbitraries.integers().array(int[].class).ofMinSize(10001)
                .map(integers -> integers);
    }
}