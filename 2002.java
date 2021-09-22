class Solution {
    public int maxProduct(String s) {
        int[] dp = new int[4096];
        int res = 0, mask = 1 << (s.length());
        for (int i = 1; i < mask; i++) {
            dp[i] = palinSize(s, i);
        }
        for (int i = mask - 1; i > 0; i--) {
            if (dp[i] * (s.length() - dp[i]) > res) {
                for (int j = (mask - 1) ^ i; j > 0; j = (j - 1) & ((mask - 1) ^ i)) {
                    res = Math.max(res, dp[i] * dp[j]);
                }
            }
        }
        return res;
    }

    private int palinSize(String s, int mask) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        while (mask > 0) {
            if ((mask & 1) == 1)
                sb.append(s.charAt(i));
            i--;
            mask >>= 1;
        }
        String reverse = sb.toString();
        if (sb.reverse().toString().equals(reverse))
            return reverse.length();
        return 0;
    }
}