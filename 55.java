class Solution {
    public boolean canJump(int[] nums) {
        int end =0;
        int maxPosition = 0;
        int step =0;
        for (int i =0; i<nums.length-1; i++) {
            if (i>end) return false;
            maxPosition = Math.max(maxPosition, i+nums[i]);
            if (i==end) {
                end = maxPosition;
                step++;
            }
        }
        return end>=nums.length-1;
    }
}