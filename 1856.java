class Solution {
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = -1;
        for (int i = 1; i<n; i++) {
            int j = i-1;
            while (j!=-1 && nums[j]>=nums[i]) {
                j = left[j];
            }
            left[i] = j;
        }
        right[n-1] = n;
        for (int i = n-2; i>=0; i--) {
            int j = i+1;
            while (j!=n && nums[j]>=nums[i]) {
                j = right[j];
            }
            right[i] = j;
        }
        long maxSum = 0;
        long[] sum = new long[n+1];
        for (int i = 1; i<n+1; i++) {
            sum[i] = sum[i-1]+nums[i-1];
        }
        for (int i = 0; i<n; i++) {
            long candidate = (sum[right[i]]-sum[left[i]+1])*((long)nums[i]);
            maxSum = Math.max(maxSum, candidate);
        }
        return (int)(maxSum%1_000_000_007);
    }
}