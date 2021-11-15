class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] players = new int[n][2];
        for (int i = 0; i < n; i++) {
            players[i][0] = scores[i];
            players[i][1] = ages[i];
        }
        Arrays.sort(players, (a, b) -> (a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]));
        int[] dp = new int[n];
        dp[0] = players[0][0];
        int res = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = players[i][0];
            for (int j = 0; j < i; j++) {
                if (players[j][0] <= players[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + players[i][0]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}