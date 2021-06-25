class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        if (6*nums1.length<nums2.length || nums1.length>6*nums2.length) return -1;
        int diff = 0;
        for (int num: nums1) {
            diff+=num;
        }
        for (int num: nums2) {
            diff-=num;
        }
        if (diff<0) return minOperations(nums2, nums1);
        
        //diff>0
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = nums1.length-1, j = 0;
        int res = 0;
        while (diff>0) {
            if (j>=nums2.length || nums1[i]-1>6-nums2[j]) {
                diff-=(nums1[i--]-1);
            } else {
                diff-=(6-nums2[j++]);
            }
            res++;
        }
        return res;
    }
}