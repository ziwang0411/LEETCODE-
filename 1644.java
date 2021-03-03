/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    TreeNode answer = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        search(root, p, q);
        return answer;
    }
    
    private boolean search(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return false;
        
        int left = search(root.left, p, q)?1:0;
        int right = search(root.right, p, q)?1:0;
        int mid = root==p||root==q? 1:0;
        
        if ((left+right+mid)>=2) answer = root;
        return left+right+mid>=1;
    }
}