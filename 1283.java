class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int maxVal = Integer.MIN_VALUE;
        for (int i =0; i<nums.length; i++) {
            maxVal = Math.max(maxVal, nums[i]);
        }
        int l =1, r = maxVal;
        while (l<=r) {
            int mid = l+(r-l)/2;
            long result = calculate(nums, mid);
            if (result>threshold) {
                l=mid+1;
            } else {
                r = mid-1;
            }
        }
        return l;
    }
    private long calculate(int[] nums, int x) {
        long s = 0;
        for (int n:nums) {
            s+=n/x+(n%x==0?0:1);
        }
        return s;
    }
}