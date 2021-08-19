class Solution {
    public int missingNumber(int[] nums) {
        int sum = (1 + nums.length) * nums.length / 2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }
}

class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            n ^= i ^ nums[i];
        }
        return n;
    }
}