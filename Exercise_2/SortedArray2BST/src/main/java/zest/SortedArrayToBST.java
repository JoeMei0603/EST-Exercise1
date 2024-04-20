package zest;
import java.util.*;

class TreeNode {
    int val;
    zest.TreeNode left;
    zest.TreeNode right;
    TreeNode(int x) { val = x; }
}

public class SortedArrayToBST {

    public zest.TreeNode sortedArrayToBST(int[] nums) {
        // Check pre-conditions
        checkIfArrayNull(nums);
        if (nums.length == 0) {
            return null;
        }
        checkIfArrayTooLong(nums);
        checkIfArrayIsSortedAndUnique(nums);

        TreeNode resultingBSTTree = constructBSTRecursive(nums, 0, nums.length - 1);

        // Check post-conditions
        compareInputAndResultSize(nums, resultingBSTTree);

        return resultingBSTTree;
    }

    private zest.TreeNode constructBSTRecursive(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        zest.TreeNode node = new zest.TreeNode(nums[mid]);
        node.left = constructBSTRecursive(nums, left, mid - 1);
        node.right = constructBSTRecursive(nums, mid + 1, right);

        // Check invariants before returning node
        checkLeftAndRightNode(node);
        int rightDepth = getNodeDepth(node.right);
        int leftDepth= getNodeDepth(node.left);
        int heightDiff = rightDepth - leftDepth;
        if (Math.abs(heightDiff) > 1) {
            throw new IllegalArgumentException("Depth difference between left and right node is larger than 1!");
        }

        return node;
    }

    public List<Integer> levelOrderTraversal(zest.TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<zest.TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            zest.TreeNode current = queue.poll();
            result.add(current.val);

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }

    private void checkIfArrayNull(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("The input array nums may not be null!");
        }
    }

    private void checkIfArrayTooLong(int[] nums) {
        if (nums.length > 10000) {
            throw new IllegalArgumentException("The input array nums has more than 10^4 entries!");
        }
    }

    private void checkIfArrayIsSortedAndUnique(int[] nums) {
        Set<Integer> uniqueInputSet = new HashSet<>();

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i+1]) {
                throw new IllegalArgumentException("The input array nums must be in ascending order!");
            }
            if (!uniqueInputSet.add(nums[i])) {
                throw new IllegalArgumentException("The input array nums contain unique integers!");
            }
        }

        // Check last element for uniqueness as well
        if (!uniqueInputSet.add(nums[nums.length - 1])) {
            throw new IllegalArgumentException("The input array nums contains unique integers!");
        }
    }

    private void compareInputAndResultSize(int[] nums, TreeNode resultBSTTree) {
        int treeNodeLength = levelOrderTraversal(resultBSTTree).size();
        if (nums.length != treeNodeLength) {
            throw new IllegalArgumentException("The input array and resulting tree do not have the same amount of integers!");
        }
    }

    private void checkLeftAndRightNode(TreeNode nodeToCheck) {
        if (nodeToCheck.left != null && nodeToCheck.left.val >= nodeToCheck.val) {
            throw new IllegalArgumentException("The left-child node is larger than its parent-node.");
        }

        if (nodeToCheck.right != null && nodeToCheck.right.val <= nodeToCheck.val) {
            throw new IllegalArgumentException("The left-child node is larger than its parent-node.");
        }
    }

    private int getNodeDepth(TreeNode root)
    {
        if(root!=null)
            return 1+ Math.max(getNodeDepth(root.left), getNodeDepth(root.right));
        else
            return 0;
    }
}
