class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int res = 0;
        Map<Integer, Integer>[] count = new Map[n];
        for (int i = 0; i < n; i++) {
            count[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long diff = (long) (nums[i]) - (long) (nums[j]);
                if (diff < Integer.MIN_VALUE || diff > Integer.MAX_VALUE)
                    continue;
                int key = (int) diff;
                int sum = count[j].getOrDefault(key, 0);
                int origin = count[i].getOrDefault(key, 0);
                count[i].put(key, origin + sum + 1);
                res += sum;
            }
        }
        return res;
    }
}