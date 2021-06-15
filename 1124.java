class Solution {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] performance = new int[n];
        for (int i = 0; i < n; i++) {
            if (hours[i] > 8)
                performance[i] = 1;
            else
                performance[i] = -1;
        }
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + performance[i];
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= n; i++) {
            if (stack.isEmpty() || preSum[stack.peek()] > preSum[i])
                stack.push(i);
        }
        int res = 0;
        for (int i = n; i >= 0; i--) {
            while (!stack.isEmpty() && preSum[stack.peek()] < preSum[i]) {
                res = Math.max(res, i - stack.pop());
            }
        }
        return res;
    }
}