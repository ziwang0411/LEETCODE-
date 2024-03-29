class Solution {
    public int findDerangement(int n) {
        // f(n)=(n-1)*(f(n-1)+f(n-2));
        if (n == 0)
            return 1;
        if (n == 1)
            return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = (int) (((i - 1L) * (dp[i - 1] + dp[i - 2])) % 1_000_000_007);
        }
        return dp[n];
    }
}