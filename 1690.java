class Solution {
    int n;
    int[] stones;
    Integer[][] dp;

    public int stoneGameVII(int[] stones) {
        n = stones.length;
        dp = new Integer[n][n];
        this.stones = stones;
        int sum = 0;
        for (int s : stones)
            sum += s;
        return play(0, n - 1, sum);
    }

    private int play(int left, int right, int sum) {
        if (left >= right)
            return 0;
        if (dp[left][right] != null)
            return dp[left][right];

        int res = sum - stones[left] - play(left + 1, right, sum - stones[left]);
        res = Math.max(res, sum - stones[right] - play(left, right - 1, sum - stones[right]));
        dp[left][right] = res;
        return res;
    }
}