class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        int[] clone = queries.clone();
        Arrays.sort(clone);
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - a[0] - b[1] + b[0]));
        int index = 0;
        for (int q : clone) {
            while (index < intervals.length && intervals[index][0] <= q) {
                pq.add(intervals[index++]);
            }
            while (!pq.isEmpty() && pq.peek()[1] < q)
                pq.poll();
            if (pq.isEmpty())
                map.put(q, -1);
            else
                map.put(q, pq.peek()[1] - pq.peek()[0] + 1);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = map.get(queries[i]);
        }
        return res;
    }
}