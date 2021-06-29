class MyHashMap {
    Bucket[] buckets;
    int keyRange;

    /** Initialize your data structure here. */
    public MyHashMap() {
        keyRange = 2069;
        buckets = new Bucket[keyRange];
        for (int i = 0; i < keyRange; i++) {
            buckets[i] = new Bucket();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = key % keyRange;
        buckets[index].put(key, value);
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map
     * contains no mapping for the key
     */
    public int get(int key) {
        int index = key % keyRange;
        return buckets[index].get(key);
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping
     * for the key
     */
    public void remove(int key) {
        int index = key % keyRange;
        buckets[index].remove(key);
    }
}

class Bucket {
    List<int[]> list;

    public Bucket() {
        list = new ArrayList<>();
    }

    public void put(int key, int value) {
        boolean containsKey = false;
        for (int[] pair : list) {
            if (pair[0] == key) {
                pair[1] = value;
                containsKey = true;
                break;
            }
        }
        if (!containsKey)
            list.add(new int[] { key, value });
    }

    public int get(int key) {
        for (int[] pair : list) {
            if (pair[0] == key) {
                return pair[1];
            }
        }
        return -1;
    }

    public void remove(int key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[0] == key) {
                list.remove(i);
                break;
            }
        }
    }
}
/**
 * Your MyHashMap object will be instantiated and called as such: MyHashMap obj
 * = new MyHashMap(); obj.put(key,value); int param_2 = obj.get(key);
 * obj.remove(key);
 */