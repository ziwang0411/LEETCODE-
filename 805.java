class Solution {
    Map<String, Boolean> memo; 
    public boolean splitArraySameAverage(int[] nums) {
        int sum = 0;
        memo = new HashMap<>();
        for (int num: nums) {
            sum+=num;
        }
        int n = nums.length;
        for (int i = 1; i<n/2+1; i++) {
            if (sum*i%n==0) {
                if (check(nums, sum*i/n, i, 0)) return true;
            }
        }
        return false;
    }
    
    private boolean check(int[] nums, int leftSum, int leftNum, int start) {
        if (leftNum==0) return leftSum == 0;
        if (nums.length-start<leftNum) return false;
        String key = leftSum+","+leftNum+","+start;
        if (memo.containsKey(key)) return memo.get(key);
        
        boolean res = check(nums, leftSum-nums[start], leftNum-1, start+1) || check(nums, leftSum, leftNum, start+1);
        memo.put(key, res);
        return res;
    }
}