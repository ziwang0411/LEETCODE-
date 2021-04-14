class AllOne {
    HashMap<String, Integer> stringToCounts;
    HashMap<Integer, Bucket> countToBuckets;
    Bucket head;
    Bucket tail;

    /** Initialize your data structure here. */
    public AllOne() {
        stringToCounts = new HashMap<>();
        countToBuckets = new HashMap<>();
        head = new Bucket(Integer.MIN_VALUE);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (stringToCounts.containsKey(key)) {
            changeKey(key, 1);
        } else {
            stringToCounts.put(key, 1);
            if (head.next.count != 1) {
                addBucketAfter(new Bucket(1), head);
            }
            head.next.keySet.add(key);
            countToBuckets.put(1, head.next);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data
     * structure.
     */
    public void dec(String key) {
        if (stringToCounts.containsKey(key)) {
            int count = stringToCounts.get(key);
            if (count == 1) {
                stringToCounts.remove(key);
                removeKeyFromBucket(countToBuckets.get(count), key);
            } else {
                changeKey(key, -1);
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail.prev == head ? "" : (String) tail.prev.keySet.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.next == tail ? "" : (String) head.next.keySet.iterator().next();

    }

    private void changeKey(String key, int offset) {
        int count = stringToCounts.get(key);
        stringToCounts.put(key, count + offset);
        Bucket currBucket = countToBuckets.get(count);
        Bucket newBucket;
        if (countToBuckets.containsKey(count + offset)) {
            newBucket = countToBuckets.get(count + offset);
        } else {
            newBucket = new Bucket(count + offset);
            countToBuckets.put(count + offset, newBucket);
            addBucketAfter(newBucket, offset == 1 ? currBucket : currBucket.prev);
        }
        newBucket.keySet.add(key);
        removeKeyFromBucket(currBucket, key);
    }

    private void removeKeyFromBucket(Bucket bucket, String key) {
        bucket.keySet.remove(key);
        if (bucket.keySet.size() == 0) {
            bucket.prev.next = bucket.next;
            bucket.next.prev = bucket.prev;
            bucket.next = null;
            bucket.prev = null;
            countToBuckets.remove(bucket.count);
        }
    }

    private void addBucketAfter(Bucket curr, Bucket prev) {
        curr.prev = prev;
        curr.next = prev.next;
        prev.next.prev = curr;
        prev.next = curr;
    }
}

class Bucket {
    int count;
    Set<String> keySet;
    Bucket prev;
    Bucket next;

    public Bucket(int cnt) {
        count = cnt;
        keySet = new HashSet<>();
    }
}

/**
 * Your AllOne object will be instantiated and called as such: AllOne obj = new
 * AllOne(); obj.inc(key); obj.dec(key); String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */

/**
 * Method 2: Use bucket only
 */

class AllOne {
    HashMap<String, Integer> strings;
    TreeMap<Integer, Set<String>> counts;

    /** Initialize your data structure here. */
    public AllOne() {
        strings = new HashMap<>();
        counts = new TreeMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (!strings.containsKey(key)) {
            strings.put(key, 0);
        }
        int count = strings.get(key);
        if (counts.containsKey(count)) {
            // System.out.println(key+" , "+ (count));
            counts.get(count).remove(key);
            if (counts.get(count).isEmpty()) {
                counts.remove(count);
            }
        }
        strings.put(key, count + 1);
        if (!counts.containsKey((count + 1))) {
            counts.put(count + 1, new HashSet<String>());
        }
        counts.get(count + 1).add(key);
        // System.out.println(counts.get(count+1).iterator().next());

    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data
     * structure.
     */
    public void dec(String key) {
        int count = strings.get(key);
        if (count == 1) {
            strings.remove(key);
        } else {
            strings.put(key, count - 1);
        }
        counts.get(count).remove(key);
        if (counts.get(count).isEmpty()) {
            counts.remove(count);
        }
        if (count > 1) {
            if (!counts.containsKey(count - 1)) {
                counts.put(count - 1, new HashSet<String>());
            }
            counts.get(count - 1).add(key);

        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (counts.isEmpty())
            return "";
        int count = counts.lastKey();
        Set<String> set = counts.get(count);
        if (set.isEmpty())
            return "";
        return set.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (counts.isEmpty())
            return "";
        int count = counts.firstKey();
        // System.out.println(count);
        Set<String> set = counts.get(count);
        if (set.isEmpty())
            return "";
        return set.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such: AllOne obj = new
 * AllOne(); obj.inc(key); obj.dec(key); String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */