class Solution {
    public double new21Game(int n, int k, int maxPts) {
        // 1 1 1 k 1 1 1 n
        // 1, 1/3, 1/3+1/9, 1/3+1/9+1/27, (dp[1]+dp[2]+dp[3])/3, (dp[2]+dp[3])/3
        if (k == 0 || n >= k + maxPts)
            return 1;
        double[] dp = new double[n + 1];
        double lastXPoints = 1;
        dp[0] = 1;
        double res = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = lastXPoints / maxPts;
            if (i < k) {
                lastXPoints += dp[i];
            } else {
                res += dp[i];
            }
            if (i - maxPts >= 0)
                lastXPoints -= dp[i - maxPts];
        }
        return res;
    }
}