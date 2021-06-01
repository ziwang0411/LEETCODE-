class Solution {
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i<n; i++) {
            if (nums[n-1-i]>=i+1 && (n-1-i==0 ||nums[n-1-i-1]<i+1)) return i+1;
        }
        return -1;
    }
}