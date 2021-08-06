class Solution {
    int[] nums;
    Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
                int possibility = rand.nextInt(count);
                if (possibility == 0)
                    res = i;
            }
        }
        if (res == -1)
            return -1;
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(nums); int param_1 = obj.pick(target);
 */