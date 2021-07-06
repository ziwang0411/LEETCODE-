class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, maxLen = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k)
                maxLen = i + 1;
            else if (map.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - map.get(sum - k));
            }
            // do not update sum in the map if there is already value stored. This will help
            // maintain the longest subarray possible.
            if (!map.containsKey(sum))
                map.put(sum, i);
        }
        return maxLen;
    }
}
