class Solution {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int num: nums) {
            sum+=num;
        }
        int target = sum-x;
        if (target<0) return -1;
        sum =0;
        int maxLen = -1, left = 0, right =0;
        while (right<nums.length) {
            sum+=nums[right];
            right++;
            while (sum>target && left<=right) {
                sum-=nums[left];
                left++;
            }
            if (sum==target) {
                maxLen = Math.max(maxLen, right-left);
            }
        }
        return maxLen==-1? -1: nums.length-maxLen;
    }
}