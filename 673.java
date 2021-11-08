class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] len = new int[n];
        int[] count=new int[n];
        
        int maxLen = 0;
        int res = 0;
        for (int i = 0; i<n; i++) {
            len[i]=1;
            count[i]=1;
            for (int j = 0; j<i; j++) {
                if (nums[i]>nums[j]) {
                    if (len[j]+1==len[i]) count[i]+=count[j];
                    if (len[i]<len[j]+1) {
                        count[i]=count[j];
                        len[i]=len[j]+1;
                    }
                }
            }
            if (maxLen==len[i]) res+=count[i];
            if (maxLen<len[i]) {
                res = count[i];
                maxLen = len[i];
            }
        }
        return res;
    }
}