class Solution {
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        double eps = 1e-9;
        Double[][] dp = new Double[n][n];
        dp[0][0] = (1.0*dist[0])/speed;
        for (int i = 1; i<n; i++) {
            dp[i][0] =  Math.ceil(dp[i-1][0])+(1.0*dist[i])/speed-eps;
        }
        
        for (int i = 0; i<n; i++) {
            for (int j = 1; j<=i; j++) {
                if (dp[i-1][j]==null) dp[i][j] = dp[i-1][j-1]+(1.0*dist[i]/speed)-eps;
                else dp[i][j] = Math.min(Math.ceil(dp[i-1][j])+(1.0*dist[i])/speed-eps, dp[i-1][j-1]+(1.0*dist[i])/speed);
            }
        }
        
        for (int j = 0; j<n; j++) {
            if (dp[n-1][j]<=(1.0*hoursBefore)) {
                return j;
            }
        }
        return -1;
    }
}