class Solution {
    int res = Integer.MAX_VALUE;
    int bucketSize;

    public int minimumIncompatibility(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            if (countMap.get(num) > k)
                return -1;
        }
        bucketSize = nums.length / k;
        List<TreeSet<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            buckets.add(new TreeSet<Integer>());
        }
        backtrack(nums, 0, buckets, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void backtrack(int[] nums, int index, List<TreeSet<Integer>> buckets, int incompatibility) {
        if (index == nums.length) {
            res = Math.min(res, incompatibility);
            return;
        }

        Set<TreeSet<Integer>> visited = new HashSet<>();
        for (TreeSet<Integer> bucket : buckets) {
            if (bucket.size() < bucketSize && !bucket.contains(nums[index]) && !visited.contains(bucket)) {
                int diffChange = calculateDiff(bucket, nums[index]);
                if (incompatibility + diffChange < res) {
                    bucket.add(nums[index]);
                    backtrack(nums, index + 1, buckets, incompatibility + diffChange);
                    bucket.remove(nums[index]);
                }
            }
            visited.add(bucket);
        }
    }

    private int calculateDiff(TreeSet<Integer> bucket, int val) {
        if (bucket.size() == 0)
            return 0;
        if (val < bucket.first())
            return bucket.first() - val;
        if (val > bucket.last())
            return val - bucket.last();
        return 0;
    }
}

class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[1 << n];
        int size = n / k;
        for (int i = 1; i < dp.length; i++) {
            int count = Integer.bitCount(i);
            if (count == size) {
                int max = 0, min = Integer.MAX_VALUE;
                int[] frequency = new int[17];
                for (int bit = 0; bit < n; bit++) {
                    if ((i & (1 << bit)) > 0) {
                        frequency[nums[bit]]++;
                        if (frequency[nums[bit]] > 1) {
                            dp[i] = -1;
                            break;
                        }
                        max = Math.max(max, nums[bit]);
                        min = Math.min(min, nums[bit]);
                    }
                }
                if (dp[i] != -1)
                    dp[i] = max - min;
            } else if (count % size == 0) {
                dp[i] = Integer.MAX_VALUE;
                for (int curr = i; curr > 0; curr = ((curr - 1) & i)) {
                    if (Integer.bitCount(curr) == size) {
                        int counterpart = i ^ curr;
                        if (dp[curr] == -1 || dp[counterpart] == -1)
                            continue;
                        dp[i] = Math.min(dp[i], dp[curr] + dp[counterpart]);
                    }
                }
                if (dp[i] == Integer.MAX_VALUE)
                    dp[i] = -1;
            }
        }
        return dp[dp.length - 1];
    }
}