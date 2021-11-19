class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int m = board.size(), n = board.get(0).length();
        int[][][] dp = new int[m][n][2];
        dp[0][0][1] = 1;
        int mod = 1_000_000_007;
        for (int i = 1; i < n; i++) {
            if (board.get(0).charAt(i) == 'X') {
                break;
            } else {
                if (dp[0][i - 1][1] > 0) {
                    dp[0][i][0] = dp[0][i - 1][0] + (board.get(0).charAt(i) - '0');
                    dp[0][i][1] = 1;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (board.get(i).charAt(0) == 'X') {
                break;
            } else {
                if (dp[i - 1][0][1] > 0) {
                    dp[i][0][0] = dp[i - 1][0][0] + (board.get(i).charAt(0) - '0');
                    dp[i][0][1] = 1;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (board.get(i).charAt(j) == 'X') {
                    dp[i][j][0] = Integer.MIN_VALUE;
                } else {
                    dp[i][j][0] = -1;
                    int value = i == m - 1 && j == n - 1 ? 0 : (board.get(i).charAt(j) - '0');
                    if (dp[i - 1][j][1] > 0) {
                        if (dp[i - 1][j][0] + value >= dp[i][j][0]) {
                            if (dp[i - 1][j][0] + value > dp[i][j][0]) {
                                dp[i][j][1] = dp[i - 1][j][1];
                                dp[i][j][0] = dp[i - 1][j][0] + value;
                            } else {
                                dp[i][j][1] = (dp[i][j][1] + dp[i - 1][j][1]) % mod;
                            }
                        }
                    }
                    if (dp[i][j - 1][1] > 0) {
                        if (dp[i][j - 1][0] + value >= dp[i][j][0]) {
                            if (dp[i][j - 1][0] + value > dp[i][j][0]) {
                                dp[i][j][1] = dp[i][j - 1][1];
                                dp[i][j][0] = dp[i][j - 1][0] + value;
                            } else {
                                dp[i][j][1] = (dp[i][j][1] + dp[i][j - 1][1]) % mod;
                            }
                        }
                    }
                    if (dp[i - 1][j - 1][1] > 0) {
                        if (dp[i - 1][j - 1][0] + value >= dp[i][j][0]) {
                            if (dp[i - 1][j - 1][0] + value > dp[i][j][0]) {
                                dp[i][j][1] = dp[i - 1][j - 1][1];
                                dp[i][j][0] = dp[i - 1][j - 1][0] + value;
                            } else {
                                dp[i][j][1] = (dp[i][j][1] + dp[i - 1][j - 1][1]) % mod;
                            }
                        }
                    }
                }
            }
        }
        if (dp[m - 1][n - 1][1] == 0)
            return new int[] { 0, 0 };
        else
            return dp[m - 1][n - 1];
    }
}