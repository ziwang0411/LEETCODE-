class Solution {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;

        int[] pows = new int[n];
        int  mod = (int)1e9 + 7;
        pows[0]=1;
        for (int i = 1 ; i < n ; ++i)
            pows[i] = pows[i - 1] * 2 % mod;

        int l = 0, r = n-1;
        long ans = 0;
        while (l<=r) {
            if (nums[l]+nums[r]<=target) {
                ans=(ans+pows[r-l])%mod;
                l++;
            } else {
                r--;
            }
        }
        return (int)(ans%1000000007);
    }
}