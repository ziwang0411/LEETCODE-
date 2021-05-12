class Solution {
    Integer[][] memo;

    public int maxSatisfaction(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        memo = new Integer[n][n + 1];
        return dp(0, 1, nums);
    }

    private int dp(int i, int t, int[] nums) {
        if (i >= nums.length)
            return 0;
        if (memo[i][t] != null)
            return memo[i][t];
        int notCook = dp(i + 1, t, nums);
        int cook = dp(i + 1, t + 1, nums) + nums[i] * t;
        memo[i][t] = Math.max(cook, notCook);
        return memo[i][t];
    }
}

class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int res = 0;
        int totalCooked = 0;
        for (int i = n - 1; i >= 0; i--) {
            totalCooked += satisfaction[i];
            if (totalCooked < 0)
                break;
            res += totalCooked;
        }
        return res;
    }
}