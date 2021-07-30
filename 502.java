class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }
        Arrays.sort(projects, (a, b) -> (a[0] == b[0] ? (b[1] - a[1]) : (a[0] - b[0])));
        int res = w;
        int times = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> (b - a));
        int index = 0;
        for (int i = 0; i <= k; i++) {
            if (!pq.isEmpty())
                res += pq.poll();
            if (index < n && pq.isEmpty() && projects[index][0] > res)
                return res;
            while (index < n && projects[index][0] <= res) {
                pq.add(projects[index++][1]);
            }
        }
        return res;
    }
}