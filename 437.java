/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    int count = 0;
    int targetSum;
    HashMap<Integer, Integer> map = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        preorder(root, 0);
        return count;
    }

    private void preorder(TreeNode node, int currSum) {
        if (node == null)
            return;
        currSum += node.val;
        if (currSum == targetSum)
            count++;
        count += map.getOrDefault(currSum - targetSum, 0);
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        preorder(node.left, currSum);
        preorder(node.right, currSum);
        map.put(currSum, map.get(currSum) - 1);
    }
}