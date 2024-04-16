package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

class MergeKSortedListsTest {
    private static MergeKSortedLists classInstance;
    private ListNode[] lists;

    @BeforeAll
    static void setupClass() {
        classInstance = new MergeKSortedLists();
    }

    @Test
    void testNullReturn() {
        ListNode[] emptyInputNode = new ListNode[0];
        ListNode resultWithNullInput = classInstance.mergeKLists(null);
        ListNode resultWithEmptyInput = classInstance.mergeKLists(emptyInputNode);

        Assertions.assertNull(resultWithNullInput);
        Assertions.assertNull(resultWithEmptyInput);
    }

    @Test
    void testZeroReturn() {
        lists = new ListNode[]{
                new ListNode(),
                null,
                new ListNode(1, new ListNode(2))
        };

        ListNode result = classInstance.mergeKLists(lists);

        Assertions.assertEquals(0, result.val);
        Assertions.assertNotNull(result.next);
    }

    @Test
    void testValidInput() {
        lists = new ListNode[]{
                new ListNode(-10000),
                new ListNode(0, new ListNode(0)),
                new ListNode(10000)
        };
        ListNode result = classInstance.mergeKLists(lists);

        Assertions.assertEquals(-10000, result.val);
        Assertions.assertEquals(0, result.next.val);
        Assertions.assertEquals(0, result.next.next.val);
        Assertions.assertEquals(10000, result.next.next.next.val);
    }

    @Test
    void testTooSmallNodeValue() {
        lists = new ListNode[]{
                new ListNode(-10001, new ListNode(-5000)),
                new ListNode(5000, new ListNode(9999))
        };

        Assertions.assertThrows(IllegalArgumentException.class, () -> classInstance.mergeKLists(lists));
    }

    @Test
    void testTooLargeNodeValue() {
        lists = new ListNode[]{
                new ListNode(-9999, new ListNode(-5000)),
                new ListNode(5000, new ListNode(10001))
        };

        Assertions.assertThrows(IllegalArgumentException.class, () -> classInstance.mergeKLists(lists));
    }

    @Test
    void testTooLargeNodeInputAmount() {
        lists = new ListNode[1];

        ListNode currentNode = null;
        for (int i = 0; i < 10001; i++) {
            ListNode nextNode = new ListNode(i);
            if (currentNode == null) {
                currentNode = nextNode;
                lists[0] = currentNode;
            } else {
                currentNode.next = nextNode;
                currentNode = nextNode;
            }
        }

        Assertions.assertThrows(IllegalArgumentException.class, () -> classInstance.mergeKLists(lists));
    }

    @Test
    void testValidLargeNodeInputAmount() {
        lists = new ListNode[1];

        ListNode currentNode = null;
        for (int i = 0; i < 10000; i++) {
            ListNode nextNode = new ListNode(i);
            if (currentNode == null) {
                currentNode = nextNode;
                lists[0] = currentNode;
            } else {
                currentNode.next = nextNode;
                currentNode = nextNode;
            }
        }
        ListNode result = classInstance.mergeKLists(lists);
        int resultSize = getSize(result);

        Assertions.assertEquals(10000, resultSize);
    }

    private int getSize(ListNode resultNode) {
        int count = 0;
        ListNode currentNode = resultNode;
        while (currentNode != null){
            count++;
            currentNode = currentNode.next;
        }
        return count;
    }

    @Property
    void testValidProperties(@ForAll("validLists") ListNode[] validLists) {
        MergeKSortedLists propertyClassInstance = new MergeKSortedLists();
        ListNode result = propertyClassInstance.mergeKLists(validLists);

        Assertions.assertTrue(result.val <= result.next.val);
        Assertions.assertTrue(result.next.val <= result.next.next.val);
    }

    @Property
    void testInValidProperties(@ForAll("invalidLists") ListNode[] invalidLists) {
        MergeKSortedLists propertyClassInstance = new MergeKSortedLists();

        Assertions.assertThrows(IllegalArgumentException.class, () -> propertyClassInstance.mergeKLists(invalidLists));
    }

    @Property
    void testDifferentValidListLengths(@ForAll @IntRange(min = 0, max = 10000) int n) {
        MergeKSortedLists propertyClassInstance = new MergeKSortedLists();
        ListNode[] lists = generateRandomListNodes(n);
        ListNode result = propertyClassInstance.mergeKLists(lists);

        Assertions.assertEquals(n, getSize(result));
    }

    @Provide
    Arbitrary<ListNode[]> validLists() {
        Arbitrary<Integer> nodeValue1 = Arbitraries.integers().between(-10000, 10000);
        Arbitrary<Integer> nodeValue2 = Arbitraries.integers().between(-10000, 10000);
        Arbitrary<Integer> nodeValue3 = Arbitraries.integers().between(-10000, 10000);

        return Combinators.combine(nodeValue1, nodeValue2, nodeValue3)
                .as((node1, node2, node3) -> new ListNode[]
                        {new ListNode(node1),
                         new ListNode(node2),
                         new ListNode(node3)});
    }

    @Provide
    Arbitrary<ListNode[]> invalidLists() {
        Arbitrary<Integer> nodeValue1 = Arbitraries.integers().between(-10000, 10000);
        Arbitrary<Integer> tooSmallValue = Arbitraries.integers().lessOrEqual(-10001);
        Arbitrary<Integer> tooLargeValue = Arbitraries.integers().greaterOrEqual(10001);

        return Combinators.combine(nodeValue1, Arbitraries.oneOf(tooSmallValue, tooLargeValue))
                .as((node1, node2) -> new ListNode[]
                        {new ListNode(node1, new ListNode(node2))});
    }

    private ListNode[] generateRandomListNodes(int size) {
        return Stream.generate(() -> new ListNode(1))
                .limit(size)
                .toArray(ListNode[]::new);
    }
}