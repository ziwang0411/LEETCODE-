class Solution {
    public int twoEggDrop(int n) {
        return find(n, 2, new int[n + 1][3]);
    }

    private int find(int floor, int egg, int[][] dp) {
        if (egg == 1 || floor == 1)
            return floor;
        if (dp[floor][egg] > 0)
            return dp[floor][egg];
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < floor; i++) {
            res = Math.min(res, 1 + Math.max(find(i - 1, egg - 1, dp), find(floor - i, egg, dp)));
        }
        dp[floor][egg] = res;
        return res;
    }
}