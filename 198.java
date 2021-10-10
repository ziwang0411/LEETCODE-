class Solution {
    public int rob(int[] nums) {
        if (nums.length==1) return nums[0];
        int rob = nums[0];
        int skip = 0;
        for (int i = 1; i<nums.length; i++) {
            int temp = rob;
            rob = skip+nums[i];
            skip = Math.max(skip, temp);
        }
        return Math.max(rob, skip);
    }
}