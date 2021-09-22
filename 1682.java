class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][][] dp = new int[n][n][26];
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (j == i + 1) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j][s.charAt(i) - 'a'] = 2;
                        res = Math.max(res, 2);
                    }
                } else {
                    for (int k = 0; k < 26; k++) {
                        if (s.charAt(i) == s.charAt(j) && (s.charAt(i) - 'a') != k) {
                            dp[i][j][s.charAt(i) - 'a'] = Math.max(dp[i + 1][j - 1][k] + 2,
                                    dp[i][j][s.charAt(i) - 'a']);
                            res = Math.max(res, dp[i][j][s.charAt(i) - 'a']);
                        }
                        dp[i][j][k] = Math.max(dp[i][j][k],
                                Math.max(dp[i + 1][j][k], Math.max(dp[i][j - 1][k], dp[i + 1][j - 1][k])));
                    }
                }
            }
        }
        return res;
    }
}