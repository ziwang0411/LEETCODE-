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
    TreeNode LCA;
    public int findDistance(TreeNode root, int p, int q) {
        findLCA(root, p, q);
        return search(LCA,p,q);
        
    }
    
    private boolean findLCA(TreeNode root, int p, int q) {
        if (root==null) return false;
        int left = findLCA(root.left, p, q)?1:0;
        int right = findLCA(root.right, p, q)?1:0;
        int mid = root.val==p || root.val==q?1:0;
        if (p==q) mid=2;
        if( (left+right+mid)==2) LCA = root;
        return (left+right+mid)>=1;
    }
    
    private int search(TreeNode root, int p, int q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len = 0;
        int found = 0;
        int result = 0;
        while (!queue.isEmpty() && found<2) {
            int sz = queue.size();
            for (int i = 0; i<sz; i++) {
                TreeNode node = queue.poll();
                if (node.val==p|| node.val==q) {
                    found++;
                    result+=len;
                }
                if (found==2) break;
                if (node.left!=null) queue.offer(node.left);
                if (node.right!=null) queue.offer(node.right);
            }
            len++;

        }
        return result;
    }
}