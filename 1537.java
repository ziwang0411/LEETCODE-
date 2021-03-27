class Solution {
    public int maxSum(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        long dp1 = 0, dp2 = 0;
        int i = 0, j = 0;
        while (i<m || j<n) {
            if (i<m && j<n && nums1[i] == nums2[j]) {
                long temp = dp1;
                dp1= Math.max(dp1, dp2) + nums1[i];
                dp2= Math.max(temp, dp2) + nums2[j];
                i++;
                j++;
            } else if (i<m && (j<n && nums1[i]<nums2[j]) || j==n) {
                dp1+=nums1[i++];
            } else { //i==n or nums1[i]>=nums2[j]
                dp2 +=nums2[j++];
            }
        }
        int mod = 1_000_000_007;
        return (int)(Math.max(dp1, dp2)%mod);
    }
}