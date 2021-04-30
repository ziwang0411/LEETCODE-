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
    List<Integer> res;
    TreeNode target;
    int K;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        res = new ArrayList<>();
        this.target = target;
        this.K = K;
        dfs(root);
        return res;
    }
    private int dfs(TreeNode node) {
        if (node==null) return -1;
        if (node==target) {
            bfs(node, K);
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        if (left==-1 && right==-1) return -1;
        if (left!=-1) {
            if (K-left==1) res.add(node.val);
            if (K-left>1) bfs(node.right, K-left-2);
            return left+1;
        }
        if (right!=-1) {
            if (K-right==1) res.add(node.val);
            if (K-right>1) bfs(node.left, K-right-2);
            return right+1;
        }
        return 0;
    }
    private void bfs(TreeNode node, int depth) {
        if (node==null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int level = depth+1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            level--;
            for (int i = 0; i<sz; i++) {
                TreeNode curr = queue.poll();
                if (level==0) {
                    res.add(curr.val);
                    continue;
                }
                if (curr.left!=null) queue.offer(curr.left);
                if (curr.right!=null) queue.offer(curr.right);
            }
        }
    }
}