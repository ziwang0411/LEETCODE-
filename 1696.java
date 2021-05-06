class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(nums[n-1]);
        int[] dp = new int[n];
        dp[n-1] = nums[n-1];
        for (int i = n-2; i>=0; i--) {
            if (i+k+1<n && deque.peekFirst()==dp[i+k+1]) deque.pollFirst();
            int sum = deque.peekFirst()+nums[i];
            while (!deque.isEmpty() && deque.peekLast()<sum) {
                deque.pollLast();
            }
            deque.addLast(sum);
            dp[i] = sum;
        }
        return dp[0];
    }
}