/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
    Node head = new Node();
    Node prev = head;

    public Node treeToDoublyList(Node root) {
        if (root == null)
            return null;
        inorder(root);
        prev.right = head.right;
        head.right.left = prev;
        return head.right;
    }

    private void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        prev.right = root;
        root.left = prev;
        prev = root;
        inorder(root.right);
    }
}