class Solution {
    public int minAbsDifference(int[] nums, int goal) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        int n = nums.length;
        int m = n / 2;
        treeSet.add(0);
        for (int i = (1 << m) - 1; i > 0; i--) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += nums[j] * ((i >> j) & 1);
            }
            treeSet.add(sum);
        }
        int res = Math.abs(goal);
        for (int i = (1 << (n - m)) - 1; i > 0; i--) {
            int sum = 0;
            for (int j = 0; j < n - m; j++) {
                sum += nums[j + m] * ((i >> j) & 1);
            }
            Integer floor = treeSet.floor(goal - sum);
            Integer ceil = treeSet.ceiling(goal - sum);
            if (floor != null)
                res = Math.min(res, Math.abs(sum - goal + floor));
            if (ceil != null)
                res = Math.min(res, Math.abs(sum - goal + ceil));
        }
        Integer floor = treeSet.floor(goal);
        Integer ceil = treeSet.ceiling(goal);
        if (floor != null)
            res = Math.min(res, Math.abs(-goal + floor));
        if (ceil != null)
            res = Math.min(res, Math.abs(-goal + ceil));
        return res;
    }
}