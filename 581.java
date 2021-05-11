class Solution {
    public int findUnsortedSubarray(int[] nums) {
        boolean flag = false;
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i])
                flag = true;
            if (flag) {
                min = Math.min(min, nums[i]);
            }
        }
        flag = false;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag) {
                max = Math.max(max, nums[i]);
            }
        }
        int l = 0;
        for (; l < nums.length; l++) {
            if (nums[l] > min)
                break;
        }
        int r = n - 1;
        for (; r >= 0; r--) {
            if (nums[r] < max)
                break;
        }
        return r > l ? r - l + 1 : 0;

    }
}