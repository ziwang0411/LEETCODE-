class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] cpuTasks = new int[n][3];
        // int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            cpuTasks[i][0] = tasks[i][0];
            cpuTasks[i][1] = tasks[i][1];

            cpuTasks[i][2] = i;
        } // start, time, index
        Arrays.sort(cpuTasks, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0])
                    return a[1] - b[1];
                return a[0] - b[0];
            }
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] == b[1] ? (a[2] - b[2]) : (a[1] - b[1])));
        int currentJobIndex = 0;
        int[] res = new int[n];
        int availableTime = cpuTasks[currentJobIndex][0];
        pq.add(cpuTasks[currentJobIndex++]);
        int temp = 0;
        while (!pq.isEmpty()) {
            int[] job = pq.poll();
            int start = job[0], duration = job[1], index = job[2];
            res[temp++] = index;
            availableTime += duration;
            while (currentJobIndex < n && cpuTasks[currentJobIndex][0] <= availableTime)
                pq.add(cpuTasks[currentJobIndex++]);
            while (currentJobIndex < n && pq.isEmpty()) {
                availableTime = cpuTasks[currentJobIndex][0];
                pq.add(cpuTasks[currentJobIndex++]);
            }
        }
        return res;
    }
}