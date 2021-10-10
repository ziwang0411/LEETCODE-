class Solution {
    public int rob(int[] nums) {
        int rob = nums[0], skip = 0;
        int n =nums.length;
        if (n==1) return nums[0];
        for (int i = 1; i<n-1; i++) {
            int temp = rob;
            rob = skip+nums[i];
            skip = Math.max(skip, temp);
        }
        int res = Math.max(rob, skip);
        rob = nums[1];
        skip = 0;
        for (int i = 2; i<n; i++) {
            int temp = rob;
            rob = skip+nums[i];
            skip = Math.max(skip, temp);
        }
        res=Math.max(res, Math.max(rob, skip));
        return res;
    }
}