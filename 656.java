class Solution {
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length;
        int[] dp = new int[n];
        int[] next = new int[n];
        Arrays.fill(next, -1);
        for (int i = n - 2; i >= 0; i--) {
            int min_cost = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + maxJump && j < n; j++) {
                if (coins[j] >= 0) {
                    int cost = coins[i] + dp[j];
                    if (cost < min_cost) {
                        min_cost = cost;
                        next[i] = j;
                    }
                }
            }
            dp[i] = min_cost;
        }
        List<Integer> res = new ArrayList<>();
        int i = 0;
        for (i = 0; i < n && next[i] > 0; i = next[i]) {
            res.add(i + 1);
        }
        if (i == n - 1 && coins[i] >= 0)
            res.add(n);
        else
            res = new ArrayList<Integer>();
        return res;
    }
}