class RandomizedCollection {
    HashMap<Integer, Set<Integer>> indexes;
    List<Integer> list;
    Random rand;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        indexes = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean result = !indexes.containsKey(val);
        int index = list.size();
        Set<Integer> indexOfVal = indexes.getOrDefault(val, new HashSet<Integer>());
        indexOfVal.add(index);
        indexes.put(val, indexOfVal);
        list.add(val);
        return result;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!indexes.containsKey(val)||indexes.get(val).size()==0) return false;
        int removeIndex = indexes.get(val).iterator().next();
        indexes.get(val).remove(removeIndex);
        int lastVal = list.get(list.size()-1);
        list.set(removeIndex, lastVal);
        indexes.get(lastVal).add(removeIndex);
        indexes.get(lastVal).remove(list.size()-1);
        list.remove(list.size()-1);
        return true;
        
        
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */