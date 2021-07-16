class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, res, new ArrayList<>());
        return res;
    }
    private void backtrack(int[] nums, int start, List<List<Integer>> res, List<Integer> list) {
        res.add(new ArrayList<>(list));
        
        for (int i = start; i<nums.length; i++) {
            if (i>start && nums[i]==nums[i-1]) continue;
            list.add(nums[i]);
            backtrack(nums, i+1, res, list);
            list.remove(list.size()-1);
        }
    }
}