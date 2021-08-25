/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode root, StringBuilder sb) {
        if (root == null)
            sb.append("null,");
        else {
            sb.append(root.val + ",");
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    int pos = 0;

    public TreeNode deserialize(String data) {
        pos = 0;
        String[] dataArray = data.split(",");
        return buildTree(dataArray);
    }

    private TreeNode buildTree(String[] data) {
        String val = data[pos++];
        if (val.equals("null"))
            return null;
        TreeNode curr = new TreeNode(Integer.parseInt(val));
        curr.left = buildTree(data);
        curr.right = buildTree(data);
        return curr;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));