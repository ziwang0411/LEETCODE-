class Solution {
    public int minCost(int n, int[] icuts) {
        Arrays.sort(icuts);
        int[] cuts = new int[icuts.length + 2];
        cuts[0] = 0;
        cuts[cuts.length - 1] = n;
        for (int i = 0; i < icuts.length; i++) {
            cuts[i + 1] = icuts[i];
        }
        int[][] dp = new int[cuts.length][cuts.length];
        for (int len = 2; len < cuts.length; len++) {
            for (int i = 0; i + len < cuts.length; i++) {
                int j = i + len;
                dp[i][j] = 100000000;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + cuts[j] - cuts[i]);
                }
            }
        }
        return dp[0][cuts.length - 1];
    }
}