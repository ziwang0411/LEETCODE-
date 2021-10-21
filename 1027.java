class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer>[] dp = new Map[n];
        int res = 0;
        for (int j = 0; j < n; j++) {
            dp[j] = new HashMap<Integer, Integer>();
            for (int i = 0; i < j; i++) {
                int diff = nums[j] - nums[i];
                int len = dp[i].getOrDefault(diff, 1) + 1;
                res = Math.max(res, len);
                dp[j].put(diff, len);
            }
        }
        return res;
    }
}