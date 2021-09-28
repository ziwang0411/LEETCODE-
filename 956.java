class Solution {
    public int tallestBillboard(int[] rods) {
        int[] dp = new int[5001];
        Arrays.fill(dp, -10000);
        dp[0] = 0;
        for (int rod : rods) {
            int[] curr = dp.clone();
            for (int d = 0; d + rod < 5001; d++) {
                dp[d + rod] = Math.max(dp[d + rod], curr[d]);
                dp[Math.abs(d - rod)] = Math.max(dp[Math.abs(d - rod)], curr[d] + Math.min(d, rod));
            }
        }
        return dp[0];
    }
}