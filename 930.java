class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int currSum = 0;
        int res = 0;
        for (int i = 0; i<nums.length; i++) {
            currSum+=nums[i];
            int target = currSum-goal;
            if (map.containsKey(target)) {
                res+=map.get(target);
            }
            int count = map.getOrDefault(currSum, 0);
            map.put(currSum, count+1);
        }
        return res;
    }
}