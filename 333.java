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
    public int largestBSTSubtree(TreeNode root) {
        int[] res = isBST(root);
        return res[2];
    }
    private int[] isBST(TreeNode node) {
        if (node==null) return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        
        int[] left = isBST(node.left);
        int[] right = isBST(node.right);
        if (left[1]<node.val && right[0]>node.val) {
            return new int[] {Math.min(node.val, left[0]), Math.max(node.val, right[1]), left[2]+right[2]+1};
        } else {
            return new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2], right[2])};
        }
    }
}