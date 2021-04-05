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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, 0, inorder.length-1);
    }
    private TreeNode build(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (inStart>inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int index = inStart;
        while (inorder[index]!=preorder[preStart]) index++;
        //20,15,7, and 15, 20, 7
        //in inorder, the index is 3, 20.left is in inorder[2:2], 20.right is preStart+1+(3-2);
        root.left = build(preorder, inorder, preStart+1, inStart, index-1);
        root.right = build(preorder, inorder, preStart+1+(index-inStart), index+1, inEnd);
        return root;
    }
}