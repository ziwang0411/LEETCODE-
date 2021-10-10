class Solution {
    public int maxSizeSlices(int[] slices) {
        int n = slices.length;
        int[][] dp = new int[n][n / 3 + 1];
        dp[1][1] = slices[1];
        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= n / 3; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 2][j - 1] + slices[i]);
            }
        }
        int res = dp[n - 1][n / 3];
        dp = new int[n][n / 3 + 1];
        dp[1][1] = slices[0];
        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= n / 3; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 2][j - 1] + slices[i - 1]);
            }
        }
        res = Math.max(res, dp[n - 1][n / 3]);
        return res;
    }
}