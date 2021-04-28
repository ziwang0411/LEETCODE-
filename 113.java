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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root==null) return res;
        if (root.val==targetSum && root.left==null && root.right==null) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            res.add(list);
            return res;
        }
        List<List<Integer>> leftChildrenSum = pathSum(root.left, targetSum-root.val);
        List<List<Integer>> rightChildrenSum = pathSum(root.right, targetSum-root.val);
        for (List<Integer> list: leftChildrenSum) {
            list.add(0, root.val);
            res.add(list);
        }
        for (List<Integer> list: rightChildrenSum) {
            list.add(0, root.val);
            res.add(list);
        }
        return res;
    }
}