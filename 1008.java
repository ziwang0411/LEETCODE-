/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    int[] preorder;
    int i = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        this.preorder = preorder;
        return buildBST(Integer.MAX_VALUE);
    }

    private TreeNode buildBST(int bound) {
        if (i == preorder.length || preorder[i] > bound)
            return null;
        TreeNode root = new TreeNode(preorder[i++]);
        root.left = buildBST(root.val);
        root.right = buildBST(bound);
        return root;
    }
}