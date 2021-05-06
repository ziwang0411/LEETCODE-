class Solution {
    Integer[] dp;
    int n;
    int[] heights;
    public int maxJumps(int[] arr, int d) {
        this.heights = arr;
        n = arr.length;
        dp = new Integer[n];
        int res = 1;
        for (int i = 0; i<n; i++) {
            res = Math.max(res, find(i, d));
        }
        return res;
    }
    
    private int find(int i, int d) {
        if (dp[i]!=null) return dp[i];
        int res = 1;
        for (int j = i-1; j>=0 && j>=i-d; j--) {
            if (heights[i]<=heights[j]) break;
            res = Math.max(res, 1+find(j,d));
        }
        for (int j = i+1; j<n && j<=i+d; j++) {
            if (heights[i]<=heights[j]) break;
            res = Math.max(res, 1+find(j, d));
        }
        dp[i] = res;
        return res;
    }
}