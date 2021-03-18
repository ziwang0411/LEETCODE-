class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        //the time slots for a single person do not overlap
        PriorityQueue<int[]> heap = new PriorityQueue<>((slot1, slot2)->(slot1[0]-slot2[0]));
        
        /**
        [[60,120],[140,210]]
        [[61,62], [70, 88]]
        8
        */
        
        for (int[] slot: slots1) {
            if (slot[1]-slot[0]>=duration) heap.offer(slot);
        }
        for (int[] slot: slots2) {
            if (slot[1]-slot[0]>=duration) heap.offer(slot);
        }
        
        while (heap.size()>1) {
            int[] slot1 = heap.poll();
            int[] slot2 = heap.peek();
            if (slot1[1]>=slot2[0]+duration) {
                List<Integer> result = new ArrayList<>();
                result.add(slot2[0]);
                result.add(slot2[0]+duration);
                return result;
            }
        }
        return new ArrayList<Integer>();
    }
}