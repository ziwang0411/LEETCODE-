class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2)
            return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (max == min)
            return 0;
        int interval = Math.max(1, (max - min) / (n - 1));
        int bucketNum = (max - min) / interval + 1;
        int[] bucketMin = new int[bucketNum];
        int[] bucketMax = new int[bucketNum];

        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        for (int num : nums) {
            int index = (num - min) / interval;
            bucketMin[index] = Math.min(bucketMin[index], num);
            bucketMax[index] = Math.max(bucketMax[index], num);
        }
        int prevMax = min;
        int maxGap = 0;
        for (int i = 0; i < bucketNum; i++) {
            if (bucketMin[i] == Integer.MAX_VALUE)
                continue;
            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }
        return maxGap;
    }
}