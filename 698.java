class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % k != 0)
            return false;
        sum /= k;
        Arrays.sort(nums);
        int[] subsets = new int[k];
        return backtrack(nums, nums.length - 1, sum, subsets);
    }

    private boolean backtrack(int[] nums, int current, int sum, int[] subsets) {
        if (current == -1) {
            for (int subset : subsets) {
                if (subset != sum)
                    return false;
            }
            return true;
        }
        boolean res = false;
        for (int i = 0; i < subsets.length; i++) {
            if (subsets[i] + nums[current] > sum)
                continue;
            subsets[i] += nums[current];
            res = res || backtrack(nums, current - 1, sum, subsets);
            subsets[i] -= nums[current];
            if (res)
                break;
        }
        return res;
    }
}