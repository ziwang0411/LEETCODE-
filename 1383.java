class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < speed.length; i++) {
            pairs.add(new int[] { speed[i], efficiency[i] });
        }
        Collections.sort(pairs, (a, b) -> (b[1] - a[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long totalSpeed = 0;
        long res = 0;
        for (int[] engineer : pairs) {
            totalSpeed += engineer[0];
            pq.add(engineer[0]);
            if (pq.size() > k) {
                totalSpeed -= pq.poll();
            }
            res = Math.max(res, totalSpeed * engineer[1]);
        }
        return (int) (res % 1000000007);
    }
}