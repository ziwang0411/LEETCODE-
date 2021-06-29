class MyHashSet {
    private Bucket[] buckets;
    private int keyRange;

    /** Initialize your data structure here. */
    public MyHashSet() {
        this.keyRange = 769;
        this.buckets = new Bucket[keyRange];
        for (int i = 0; i < keyRange; i++) {
            buckets[i] = new Bucket();
        }
    }

    public void add(int key) {
        int index = key % keyRange;
        buckets[index].insert(key);
    }

    public void remove(int key) {
        int index = key % keyRange;
        buckets[index].delete(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = key % keyRange;
        return buckets[index].contains(key);
    }
}

class Bucket {
    private LinkedList<Integer> list;

    public Bucket() {
        list = new LinkedList<>();
    }

    public void insert(int key) {
        if (!list.contains(key))
            list.addFirst(key);
    }

    public void delete(Integer key) {
        list.remove(key);
    }

    public boolean contains(int key) {
        return list.contains(key);
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such: MyHashSet obj
 * = new MyHashSet(); obj.add(key); obj.remove(key); boolean param_3 =
 * obj.contains(key);
 */