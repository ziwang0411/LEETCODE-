class Solution {
    int sum;
    HashMap<Integer, Integer> map;
    public int pathSum(int[] nums) {
        int n = nums.length;
        map = new HashMap<>();
        for (int num: nums) {
            int pos = num/10;
            int val = num%10;
            map.put(pos, val);
        }
        sum = 0;
        dfs(1,1,0);
        return sum;
    }
    private void dfs(int depth, int pos, int currSum) {
        int key = depth*10+pos;
        if (!map.containsKey(key)) {
            return;
        }
        int newSum=currSum+map.get(key);
        if (!map.containsKey((depth+1)*10+pos*2) && !map.containsKey((depth+1)*10+pos*2-1)) {
            sum+=newSum;
            return;
        }
        
        dfs(depth+1, pos*2-1, newSum);
        dfs(depth+1, pos*2, newSum);
    }
}