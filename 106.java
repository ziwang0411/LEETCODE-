/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(inorder, postorder, map, postorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, Map<Integer, Integer> map, int postRoot, int inStart,
            int inEnd) {
        if (inStart > inEnd)
            return null;
        TreeNode root = new TreeNode(postorder[postRoot]);
        int index = map.get(postorder[postRoot]);
        root.left = build(inorder, postorder, map, postRoot - (inEnd - index) - 1, inStart, index - 1);
        root.right = build(inorder, postorder, map, postRoot - 1, index + 1, inEnd);
        return root;
    }
}