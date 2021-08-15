class Solution {
    public int deleteAndEarn(int[] nums) {
        int[] count = new int[10001];
        for (int num: nums) count[num]++;
        int avoid = 0, using = 0, prev = -1;
        for (int i = 0; i<=10000; i++) {
            if (count[i]>0) {
                int max = Math.max(avoid, using);
                if (i-1!=prev) {
                    using = i*count[i]+max;
                    avoid = max;
                } else {
                    using = i*count[i]+avoid;
                    avoid = max;
                }
                prev = i;
            }
        }
        return Math.max(avoid, using);
    }
}