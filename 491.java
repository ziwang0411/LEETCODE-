class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        // Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        backtrack(nums, 0, res, new ArrayList<>());
        return new ArrayList<List<Integer>>(res);
    }

    private void backtrack(int[] nums, int start, Set<List<Integer>> res, List<Integer> list) {
        if (list.size() >= 2)
            res.add(new ArrayList<>(list));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1])
                continue;
            if (list.isEmpty() || list.get(list.size() - 1) <= nums[i]) {
                list.add(nums[i]);
                backtrack(nums, i + 1, res, list);
                list.remove(list.size() - 1);
            }
        }
    }
}

class Solution {
    List<List<Integer>> res;

    public List<List<Integer>> findSubsequences(int[] nums) {
        res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<Integer>());
        return res;
    }

    private void backtrack(int[] nums, int index, List<Integer> list) {
        if (index == nums.length) {
            if (list.size() > 1)
                res.add(new ArrayList<Integer>(list));
            return;
        }
        if (list.isEmpty() || list.get(list.size() - 1) <= nums[index]) {
            list.add(nums[index]);
            backtrack(nums, index + 1, list);
            list.remove(list.size() - 1);
        }
        // if (list.size()>0 && list.get(list.size()-1)==nums[index]) return;
        backtrack(nums, index + 1, list);
    }
}