/**
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public Node expTree(String s) {
        Stack<Node> nodes = new Stack<>();
        Stack<Character> ops = new Stack<>();
        
        for (char c: s.toCharArray()) {
            if (Character.isDigit(c)) {
                nodes.push(new Node(c));
            }
            else if (c=='(') {
                ops.push(c);
            } else if (c==')') {
                while (ops.peek()!='(') {
                    nodes.push(buildNode(nodes.pop(), ops.pop(), nodes.pop()));
                }
                ops.pop();
            } else {
                while (!ops.isEmpty() && superior(ops.peek(), c)) {
                    nodes.push(buildNode(nodes.pop(), ops.pop(), nodes.pop()));
                }
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            nodes.push(buildNode(nodes.pop(), ops.pop(), nodes.pop()));
        }
        return nodes.peek();
    }
    
    private boolean superior(char a, char b) {
        if (a =='(') return false;
        return (a=='*'|| a=='/'|| b =='+'|| b=='-');
    }
    private Node buildNode(Node a, char op, Node b) {
        return new Node(op, b, a);
    }
}