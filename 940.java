class Solution {
    public int distinctSubseqII(String s) {
        // a,b,a,c,a
        // 1,2,4,8-1,14,28-4
        int n = s.length();
        int mod = 1_000_000_007;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            int x = s.charAt(i) - 'a';
            dp[i + 1] = dp[i] * 2 % mod;
            if (last[x] != -1) {
                dp[i + 1] -= dp[last[x]];
            }
            dp[i + 1] %= mod;
            last[x] = i;
        }
        dp[n]--;
        if (dp[n] < 0)
            dp[n] += mod;
        return dp[n];
    }
}