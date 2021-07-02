class Skiplist {
    private List<Node> levels = new ArrayList<>();
    Random rand;
    double DEFAULT_PROB = 0.5;

    public Skiplist() {
        rand = new Random();
        levels.add(new Node(-1));
    }

    public boolean search(int target) {
        Node smallerOrEquals = getSmallerOrEquals(target);
        return smallerOrEquals.val == target;
    }

    private Node getSmallerOrEquals(int target) {
        Node curr = levels.get(levels.size() - 1);
        while (curr != null) {
            if (curr.right == null || curr.right.val > target) {
                if (curr.down == null)
                    break;
                curr = curr.down;
            } else {
                curr = curr.right;
            }
        }
        return curr;
    }

    public void add(int num) {
        Node smallerOrEquals = getSmallerOrEquals(num);
        Node toInsert = new Node(num);
        addNodeToRight(smallerOrEquals, toInsert);
        populateLevelUp(toInsert);
    }

    private void addNodeToRight(Node leftOldNode, Node newNode) {
        Node rightOldNode = leftOldNode.right;
        leftOldNode.right = newNode;
        newNode.left = leftOldNode;
        if (rightOldNode != null) {
            newNode.right = rightOldNode;
            rightOldNode.left = newNode;
        }
    }

    private void populateLevelUp(Node node) {
        Node leftNode = node.left, curr = node;
        while (flipCoin()) {
            while (leftNode.left != null && leftNode.up == null) {
                leftNode = leftNode.left;
            }
            if (leftNode.up == null) {
                Node newSentinal = new Node(-1);
                newSentinal.down = leftNode;
                leftNode.up = newSentinal;
                levels.add(newSentinal);
            }
            leftNode = leftNode.up;
            Node UpNode = new Node(node.val);
            UpNode.down = curr;
            curr.up = UpNode;
            curr = curr.up;
            leftNode.right = curr;
            curr.left = leftNode;
        }
    }

    private boolean flipCoin() {
        return rand.nextDouble() < DEFAULT_PROB;
    }

    public boolean erase(int num) {
        Node smallerOrEquals = getSmallerOrEquals(num);
        if (smallerOrEquals.val != num)
            return false;
        Node curr = smallerOrEquals;
        while (curr != null) {
            Node left = curr.left, right = curr.right;
            left.right = right;
            if (right != null) {
                right.left = left;
            }
            curr = curr.up;
        }
        return true;
    }
}

class Node {
    int val;
    Node left, right, up, down;

    public Node(int v) {
        val = v;
    }
}

/**
 * Your Skiplist object will be instantiated and called as such: Skiplist obj =
 * new Skiplist(); boolean param_1 = obj.search(target); obj.add(num); boolean
 * param_3 = obj.erase(num);
 */