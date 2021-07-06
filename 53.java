class Solution {
    public int maxSubArray(int[] nums) {
        int bestSum = -1000001;
        int currSum = -1000001;
        for (int num: nums) {
            currSum = Math.max(num, currSum+num);
            bestSum = Math.max(bestSum, currSum);
        }
        return bestSum;
    }
}