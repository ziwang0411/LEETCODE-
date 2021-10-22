class Solution {
    private static int mod = 1_000_000_007;
    private int m; // length of target word
    private int l; // length of each word
    private String target;
    private Long[][] dp;
    private int[][] count;

    public int numWays(String[] words, String target) {
        int n = words.length;
        m = target.length();
        l = words[0].length();
        this.target = target;
        dp = new Long[l][m];
        count = new int[l][26];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < n; j++) {
                count[i][words[j].charAt(i) - 'a']++;
            }
        }
        return (int) dfs(0, 0);
    }

    private long dfs(int wordIndex, int index) {
        if (index == m)
            return 1;
        if (wordIndex == l)
            return 0;
        if (dp[wordIndex][index] != null)
            return dp[wordIndex][index];
        long res = dfs(wordIndex + 1, index);
        if (count[wordIndex][target.charAt(index) - 'a'] > 0) {
            res += (count[wordIndex][target.charAt(index) - 'a'] * dfs(wordIndex + 1, index + 1)) % mod;
            res %= mod;
        }
        return dp[wordIndex][index] = res;
    }
}