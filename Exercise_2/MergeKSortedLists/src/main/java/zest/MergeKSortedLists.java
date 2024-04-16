package zest;

import java.util.Arrays;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Check that total amount of nodes is <= 10^4 and values are >=-10^4 and <=10^4
        int totalNodes = 0;
        for (ListNode currentNode : lists) {
            int currentNodeCount = 0;
            while (currentNode != null) {
                if (currentNode.val < -10000 || currentNode.val > 10000) {
                    throw new IllegalArgumentException(
                            "The node value " + currentNode.val + " is outside the range <-10^5 and >10^5");
                }
                currentNodeCount++;
                currentNode = currentNode.next;
            }
            totalNodes += currentNodeCount;
            if (totalNodes > 10000) {
                throw new IllegalArgumentException("The total amount of nodes exceeds 10000 entries which is not allowed!");
            }
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        int iterationCount = 0;
        int currentValue = 0;
        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            // Ensure that list stays ordered after first iteration during which currentValue is always 0
            if (iterationCount > 0 && currentValue > tail.val) {
                throw new RuntimeException("The list is not ordered since a larger value follows a smaller one!");
            }
            currentValue = tail.val;

            if (tail.next != null) {
                queue.add(tail.next);
            }

            if (iterationCount < totalNodes) {
                iterationCount++;
            }
            else {
                throw new RuntimeException("The amount of iterations is larger than the amount of nodes!");
            }
        }

        return dummy.next;
    }
}
