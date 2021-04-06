/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    int index;
    int[] voyage;
    List<Integer> result;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        index = 0;
        this.voyage = voyage;
        result = new ArrayList<>();
        dfs(root);
        return result;
    }

    private void dfs(TreeNode root) {
        if (root == null)
            return;
        if (root.val != voyage[index++]) {
            result.clear();
            result.add(-1);
            return;
        }

        if (root.left != null && root.left.val != voyage[index]) {
            if (result.isEmpty() || result.get(0) != -1) {
                result.add(root.val);
            }
            dfs(root.right);
            dfs(root.left);
        } else {
            dfs(root.left);
            dfs(root.right);
        }
    }
}