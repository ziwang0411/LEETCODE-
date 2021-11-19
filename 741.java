class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, Integer.MIN_VALUE);
        dp[0][0] = grid[0][0];

        for (int t = 1; t <= 2 * n - 2; t++) {
            int[][] dp2 = new int[n][n];
            for (int[] row : dp2)
                Arrays.fill(row, Integer.MIN_VALUE);
            for (int i = Math.max(0, t - (n - 1)); i <= Math.min(n - 1, t); i++) {
                for (int j = Math.max(0, t - (n - 1)); j <= Math.min(n - 1, t); j++) {
                    if (grid[i][t - i] == -1 || grid[j][t - j] == -1)
                        continue;
                    int val = grid[i][t - i];
                    if (i != j)
                        val += grid[j][t - j];
                    for (int pi = i - 1; pi <= i; pi++) {
                        for (int pj = j - 1; pj <= j; pj++) {
                            if (pi >= 0 && pj >= 0) {
                                dp2[i][j] = Math.max(dp2[i][j], dp[pi][pj] + val);
                            }
                        }
                    }
                }
            }
            dp = dp2;
        }
        return Math.max(0, dp[n - 1][n - 1]);
    }
}