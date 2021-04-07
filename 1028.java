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
    int index;
    public TreeNode recoverFromPreorder(String S) {
        Stack<TreeNode> stack = new Stack<>();
        index = 0;
        int rootVal = parseNum(S, index);
        TreeNode root = new TreeNode(rootVal);
        stack.push(root);
        while (index<S.length()) {
            int dashes = 0;
            while (index<S.length() && S.charAt(index)=='-') {
                dashes++;
                index++;
            }
            if (dashes==stack.size()) {

                int nodeVal = parseNum(S, index);
                TreeNode parent = stack.peek();
                TreeNode child = new TreeNode(nodeVal);
                parent.left = child;
                stack.push(child);
            } else {
                while (!stack.isEmpty() && stack.size()!=dashes) {
                    stack.pop();
                }
                int nodeVal = parseNum(S, index);
                TreeNode parent = stack.peek();
                TreeNode child = new TreeNode(nodeVal);
                parent.right = child;
                stack.push(child);
            }
        }
        return root;
    }
    
    private int parseNum(String S, int leftIndex) {
        while (index<S.length() && Character.isDigit(S.charAt(index))) index++;
        int nodeVal = Integer.parseInt(S.substring(leftIndex, index));
        return nodeVal;
    }
}