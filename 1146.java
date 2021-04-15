class SnapshotArray {
    List<TreeMap<Integer,Integer>> array;
    int currentId;
    public SnapshotArray(int length) {
        array = new ArrayList<>();
        for (int i = 0; i<length; i++) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            map.put(0,0);
            array.add(map);
        }
        currentId = 0;
    }
    
    public void set(int index, int val) {
        TreeMap<Integer, Integer> map = array.get(index);
        map.put(currentId, val);
    }
    
    public int snap() {
        currentId++;
        return currentId-1;
    }
    
    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> map = array.get(index);
        int version = map.floorKey(snap_id);
        
        return map.get(version);
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */