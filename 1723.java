class Solution {
    int[] workingTime = new int[12];
    int res = Integer.MAX_VALUE;
    public int minimumTimeRequired(int[] jobs, int k) {
        if (k==jobs.length) {
            int max=0;
            for (int job: jobs) max= Math.max(max, job);
            return max;
        }
        
        dfs(jobs, 0, k, 0);
        return res;
    }
    private void dfs(int[] jobs, int i, int k, int curr) {
        if (curr >= res)
            return;
        
        if (i == jobs.length) {
            res = curr;
            return;
        }
        
        HashSet<Integer> visited = new HashSet();
        for (int j = 0; j < k; j++) {
            if (!visited.add(workingTime[j])) continue;
            workingTime[j] += jobs[i];
            dfs(jobs, i + 1, k, Math.max(curr, workingTime[j]));
            workingTime[j] -= jobs[i];
        }
        
        
    }
}