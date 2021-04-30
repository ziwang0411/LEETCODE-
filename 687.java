/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int res;
    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        findStartingFromCurrent(root);
        return res;
    }
    private int findStartingFromCurrent(TreeNode node) {
        if (node==null) return 0;
        int left = findStartingFromCurrent(node.left);
        int right = findStartingFromCurrent(node.right);
        int longestLeft = 0;
        if (node.left!=null && node.left.val==node.val) {
            longestLeft = 1+left;
        }
        int longestRight = 0;
        if (node.right!=null && node.right.val==node.val) {
            longestRight = 1+right;
        }
        res = Math.max(res, longestLeft+longestRight);
        return Math.max(longestLeft, longestRight);
    }
}