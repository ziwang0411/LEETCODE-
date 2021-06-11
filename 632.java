class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        for (int i = 0; i < n; i++) {
            for (int num : nums.get(i)) {
                pq.add(new int[] { num, i });
            }
        }
        HashMap<Integer, Integer> indexes = new HashMap<>();
        Deque<int[]> deque = new ArrayDeque<>();
        int[] res = { 0, 100000 };
        int gap = 100000;
        while (!pq.isEmpty()) {
            int[] element = pq.poll();
            int count = indexes.getOrDefault(element[1], 0);
            indexes.put(element[1], count + 1);
            deque.addLast(element);
            while (indexes.size() == n) {
                if (deque.peekLast()[0] - deque.peekFirst()[0] < gap) {
                    res[0] = deque.peekFirst()[0];
                    res[1] = deque.peekLast()[0];
                    gap = res[1] - res[0];
                }

                int[] left = deque.pollFirst();
                count = indexes.get(left[1]);
                if (count == 1)
                    indexes.remove(left[1]);
                else
                    indexes.put(left[1], count - 1);
            }
        }
        return res;
    }
}


class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(a[0]-b[0]));
        int n = nums.size();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i<n; i++) {
            int num = nums.get(i).get(0);
            pq.add(new int[] {num, i, 0});
            max = Math.max(max, num);
        }
        int start =-100000, end = 100000; 
        while (pq.size()==n) {
            int[] curr = pq.poll();
            int val = curr[0], row = curr[1], col = curr[2];
            if (end-start>(max-val)) {
                end = max;
                start = val;
            }
            if (col<nums.get(row).size()-1) {
                int next = nums.get(row).get(col+1);
                pq.add(new int[] {next, row, col+1});
                max = Math.max(max, next);
            }
        }
        return new int[] {start, end};
    }
}