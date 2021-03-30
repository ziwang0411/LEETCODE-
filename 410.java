class Solution {
    public int splitArray(int[] nums, int m) {
        long l = 0, r = 0;
        int n = nums.length;
        for (int i = 0; i<n; i++) {
            r +=nums[i];
            if (l<nums[i]) l = nums[i];
        }
        long ans = r;
        while (l<=r) {
            long mid = (l+(r-l)/2);
            long sum = 0;
            int count = 1;
            for (int i = 0; i<n; i++) {
                if (sum+nums[i]>mid) {
                    count++;
                    sum = nums[i];
                } else {
                    sum+=nums[i];
                }
            }
            if (count<=m) { //can have maxSubarray of mid with groups <=m
                r = mid-1;
            } else {
                l = mid+1;                
            }
        }
        ans = Math.min(ans, l);
        return (int)ans;
    }
}