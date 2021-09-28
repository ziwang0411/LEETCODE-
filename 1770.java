class Solution {
    Integer[][] memo;
    public int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        this.memo = new Integer[m][m];
        return dp(nums, multipliers, 0,0);
    }
    
    private int dp(int[] nums, int[] multipliers, int start, int i) {
        if (i==multipliers.length) return 0;
        if (memo[start][i]!=null) return memo[start][i];
        int useLeft = nums[start]*multipliers[i]+dp(nums,multipliers, start+1, i+1);
        int useRight = nums[nums.length-i+start-1]*multipliers[i] + dp(nums, multipliers, start, i+1);
        int score = Math.max(useLeft, useRight);
        memo[start][i]=score;
        return score;
    }
}