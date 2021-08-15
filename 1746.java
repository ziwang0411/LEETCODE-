class Solution {
    public int maxSumAfterOperation(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n+1][2];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i<n+1; i++) {
            dp[i][0] = Math.max(nums[i-1], dp[i-1][0]+nums[i-1]);
            dp[i][1] = Math.max(dp[i-1][0]+nums[i-1]*nums[i-1], Math.max(dp[i-1][1]+nums[i-1], nums[i-1]*nums[i-1]));
            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }
        return res;
    }
}