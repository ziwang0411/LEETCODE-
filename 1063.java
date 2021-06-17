class Solution {
    public int validSubarrays(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] rightSmaller = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i])
                stack.pop();
            if (stack.isEmpty())
                rightSmaller[i] = n;
            else
                rightSmaller[i] = stack.peek();
            stack.push(i);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += rightSmaller[i] - i;
        }
        return res;
    }
}