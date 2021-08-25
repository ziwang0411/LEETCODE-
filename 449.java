/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = postOrder(root, new StringBuilder());
        if (sb.length() > 0)
            sb.setLength(sb.length() - 1);
        // System.out.println(sb.toString());
        return sb.toString();
    }

    private StringBuilder postOrder(TreeNode root, StringBuilder sb) {
        if (root == null)
            return sb;
        postOrder(root.left, sb);
        postOrder(root.right, sb);
        sb.append(root.val);
        sb.append(",");
        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty())
            return null;
        ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
        for (String s : data.split(",")) {
            nums.add(Integer.parseInt(s));
        }
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);

    }

    private TreeNode helper(int lowBound, int highBound, ArrayDeque<Integer> nums) {
        if (nums.isEmpty())
            return null;
        int val = nums.getLast();
        if (val < lowBound || val > highBound)
            return null;
        nums.removeLast();
        TreeNode root = new TreeNode(val);
        root.right = helper(val, highBound, nums);
        root.left = helper(lowBound, val, nums);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;