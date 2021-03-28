class Solution {
    public int maximumScore(int[] nums, int k) {
        int result = nums[k];
        int i = k, j = k;
        int minimum = nums[k];
        while (i>0 || j<nums.length-1) {
            if (i==0) j++;
            else if (j==nums.length-1) i--;
            else if (nums[i-1]<nums[j+1]) j++;
            else i--;
            
            minimum = Math.min(minimum, Math.min(nums[i], nums[j]));
            result = Math.max(result, minimum*(j-i+1));
        }
        return result;
    }
}