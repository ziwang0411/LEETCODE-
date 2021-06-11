class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num: nums) {
            sum+=num;
        }
        if (sum%2==1) return false;
        sum = sum/2;
        int n = nums.length;
        boolean[][] dp = new boolean[n+1][sum+1];
        for (int i = 0; i<n+1; i++) {
            dp[i][0] = true;
            for (int j = 1; i>0 && j<sum+1; j++) {
                if (nums[i-1]>j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
            }
        }
        return dp[n][sum];
    }
}