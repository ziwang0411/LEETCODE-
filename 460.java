class LFUCache {
    Map<Integer, Node> nodeMap;
    Map<Integer, DLL> freqMap;
    int capacity;
    int size;
    int minFreq;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        freqMap = new HashMap<>();
        size = 0;
    }
    
    public int get(int key) {
        if (!nodeMap.containsKey(key)) return -1;
        Node n = nodeMap.get(key);
        update(n);
        return n.value;
    }
    
    public void put(int key, int value) {
        if (capacity==0) return;
        if (nodeMap.containsKey(key)) {
            Node n = nodeMap.get(key);
            n.value = value;
            update(n);
        } else {
            Node n = new Node(key, value);
            nodeMap.put(key, n);
            if (size==capacity) {
                DLL leastFrequentDLL = freqMap.get(minFreq);
                Node leastRecentlyUsed = leastFrequentDLL.getLeastRecentlyUsed();
                nodeMap.remove(leastRecentlyUsed.key);
                leastFrequentDLL.remove(leastRecentlyUsed);
                size--;
            }
            size++;
            minFreq = 1;
            DLL newList = freqMap.getOrDefault(minFreq, new DLL());
            newList.add(n);
            freqMap.put(minFreq, newList);
        }
    }
    public void update(Node node) {
        DLL oldList = freqMap.get(node.freq);
        oldList.remove(node);
        if (node.freq == minFreq && oldList.size == 0) minFreq++; 
        node.freq++;
        DLL newList = freqMap.getOrDefault(node.freq, new DLL());
        newList.add(node);
        freqMap.put(node.freq, newList);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class DLL {
    Node head;
    Node tail;
    int size;
    public DLL() {
        head = new Node(0,0);
        tail = head;
        size = 0;
    }
    public void add(Node n) {
        tail.next = n;
        n.prev = tail;
        tail = n;
        size++;
    }
    public Node getLeastRecentlyUsed() {
        return head.next;
    }
    public void moveToLast(Node n) {
        remove(n);
        add(n);
    }
    public void remove(Node n) {
        if (tail==n) {
            n.prev.next = null;
            tail = n.prev;
            n.prev = null;
        } else {
            n.prev.next = n.next;
            n.next.prev = n.prev;
            n.next = null;
            n.prev = null;
        }
        size--;
    }
}

class Node {
    Node prev;
    Node next;
    int key;
    int value;
    int freq;
    public Node(int k, int v) {
        key=k;
        value = v;
        freq = 1;
    }
}