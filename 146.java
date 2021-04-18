class LRUCache {
    int capacity;
    DoubleLinkedList dll;
    Map<Integer, Node> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        dll = new DoubleLinkedList();
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node n = map.get(key);
        dll.moveToLast(n);
        return n.value;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            n.value = value;
            dll.moveToLast(n);
        } else {
            if (map.size()==capacity) {
                Node leastRecentlyUsed = dll.head.next;
                dll.remove(leastRecentlyUsed);
                map.remove(leastRecentlyUsed.key);
                
            } 
            Node newest = new Node(key, value);
            map.put(key, newest);
            dll.add(newest);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class DoubleLinkedList {
    Node head;
    Node tail;
    public DoubleLinkedList() {
        head = new Node(0,0);
        tail = head;
    }
    public void add(Node n) {
        tail.next = n;
        n.prev = tail;
        tail = n;
    }
    public void remove(Node n) {
        n.prev.next = n.next;
        if (n.next!=null) {
            n.next.prev = n.prev;
        } else {
            tail = n.prev;
        }
        n.next = null;
        n.prev = null;
    }
    public void moveToLast(Node n) {
        remove(n);
        add(n);
    }
}

class Node {
    Node prev;
    Node next;
    int key;
    int value;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}