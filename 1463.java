class Solution {
    public int cherryPickup(int[][] grid) {
        // dp[r][c1][c2]
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][n];
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == j)
                    continue;
                dp[m - 1][i][j] = grid[m - 1][i] + grid[m - 1][j];
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k)
                        continue;
                    // from j - 1, k - 1
                    int sum = grid[i][j] + grid[i][k];
                    for (int q = -1; q <= 1; q++) {
                        for (int p = -1; p <= 1; p++) {
                            if (j + q >= 0 && j + q < n && k + p >= 0 && k + p < n && j + q != k + p) {
                                dp[i][j][k] = Math.max(dp[i + 1][j + q][k + p] + sum, dp[i][j][k]);
                            }
                        }
                    }
                }
            }
        }
        return dp[0][0][n - 1];
    }
}