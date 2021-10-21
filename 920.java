class Solution {
    public int numMusicPlaylists(int n, int goal, int k) {
        long[][] dp = new long[goal + 1][n + 1];
        int mod = 1_000_000_007;
        dp[0][0] = 1;
        for (int i = 1; i < goal + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] += dp[i - 1][j - 1] * (n - (j - 1));
                dp[i][j] += dp[i - 1][j] * Math.max(0, j - k);
                dp[i][j] %= mod;
            }
        }
        return (int) dp[goal][n];
    }
}