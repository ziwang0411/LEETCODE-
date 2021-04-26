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
    public int countNodes(TreeNode root) {
        if (root==null) return 0;
        int d = calculateDepth(root);
        if (d==0) return 1;
        
        int l = 1, r = (int)Math.pow(2,d) -1;
        while (l<=r) {
            int mid = l+(r-l)/2;
            if (exists(root, d, mid)) l = mid+1;
            else r = mid-1;
        }
        return ((int)(Math.pow(2, d))-1+l);
    }
    private int calculateDepth(TreeNode root) {
        int d = 0;
        while (root.left!=null) {
            root = root.left;
            d++;
        }
        return d;
    }
    private boolean exists(TreeNode root, int depth, int index) {
        if (depth==0) return root!=null;
        int mid =( (int)Math.pow(2,depth)-1)/2;
        if (index>mid) return exists(root.right, depth-1, index-mid-1);
        else return exists(root.left, depth-1, index);
    }
}