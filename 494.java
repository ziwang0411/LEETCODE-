class Solution {
    Integer[][] memo;

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        memo = new Integer[n][2001];
        return search(nums, target, 0, 0);
    }

    private int search(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (target == sum)
                return 1;
            return 0;
        }
        if (memo[index][sum + 1000] != null) {
            return memo[index][sum + 1000];
        }
        memo[index][sum + 1000] = search(nums, target, index + 1, sum + nums[index])
                + search(nums, target, index + 1, sum - nums[index]);
        return memo[index][sum + 1000];
    }
}