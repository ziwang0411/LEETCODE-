class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int extra = 0;
        for (int num: nums) {
            extra = (extra+num)%p;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int curr = 0;
        int res = n;
        for (int i = 0; i<n; i++) {
            curr=(curr+nums[i]) %p;
            map.put(curr, i);
            //(curr-want)%p = extra
            int want = (curr-extra+p)%p;
            if (map.containsKey(want)) {
                res = Math.min(res, i-map.get(want));
            }
        }
        return res<n? res : -1;
    }
}