class Solution {
    public int jump(int[] nums) {
        int cnt = 0, maxPosition = 0, currStepMax = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == currStepMax) {
                cnt++;
                currStepMax = maxPosition;
            }
        }
        return cnt;
    }
}