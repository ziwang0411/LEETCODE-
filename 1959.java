class Solution {
    public int minSpaceWastedKResizing(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k + 1];
        int sum = 0, maxSpace = 0;
        ;

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 200 * 1000000);
            sum += nums[i];
            maxSpace = Math.max(maxSpace, nums[i]);
            dp[i][0] = maxSpace * (i + 1) - sum;
        }
        for (int time = 1; time <= k; time++) {
            for (int j = 1; j < n; j++) {
                int currSum = 0, currMax = 0;
                for (int i = j; i > 0; i--) {
                    currSum += nums[i];
                    currMax = Math.max(currMax, nums[i]);
                    dp[j][time] = Math.min(dp[i - 1][time - 1] + (j - i + 1) * currMax - currSum, dp[j][time]);
                }
            }
        }
        return dp[n - 1][k];
    }
}