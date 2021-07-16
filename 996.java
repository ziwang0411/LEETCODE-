class Solution {
    Map<Integer, Integer> countMap = new HashMap<>();
    Map<Integer, Set<Integer>> squareMap = new HashMap<>();
    int res = 0;
    int n;

    public int numSquarefulPerms(int[] nums) {
        n = nums.length;
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            squareMap.putIfAbsent(num, new HashSet<Integer>());
        }
        for (int i : countMap.keySet()) {
            for (int j : countMap.keySet()) {
                double square = Math.sqrt(i + j);
                if (square == 1.0 * ((int) square)) {
                    squareMap.get(i).add(j);
                    squareMap.get(j).add(i);
                }
            }
        }
        for (int num : countMap.keySet()) {
            backtrack(num, 0);
        }
        return res;
    }

    private void backtrack(int curr, int len) {
        if (len == n - 1) {
            res++;
            return;
        }
        countMap.put(curr, countMap.get(curr) - 1);
        for (int next : squareMap.get(curr)) {
            if (countMap.get(next) != 0) {
                backtrack(next, len + 1);
            }
        }
        countMap.put(curr, countMap.get(curr) + 1);
    }
}