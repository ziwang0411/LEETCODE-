class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int index = -1;
        int res = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - target) && map.get(sum - target) >= index) {
                res++;
                index = i;
            }
            map.put(sum, i);
        }
        return res;
    }
}