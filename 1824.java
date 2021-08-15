class Solution {
    public int minSideJumps(int[] obstacles) {
        int n = obstacles.length;
        int[][] dp = new int[n][3];
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[0][2] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = 1000000;
                if (obstacles[i] == j + 1)
                    continue;
                else {
                    for (int k = 0; k < 3; k++) {
                        if (obstacles[i] == k + 1)
                            continue;
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + (k == j ? 0 : 1));
                    }
                }
            }
        }
        return Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
    }
}