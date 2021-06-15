class Solution {
    public int maxWidthRamp(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;

        for (int i = n - 1; i >= 0; i--) {
            if (stack.isEmpty() || nums[stack.peek()] < nums[i]) {
                stack.add(i);
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] <= nums[stack.peek()]) {
                res = Math.max(res, stack.pop() - i);
            }
        }
        return res;
    }
}