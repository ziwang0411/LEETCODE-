class Solution {
    public String largestNumber(int[] cost, int target) {
        String[] dp = new String[target+1];
        return dfs(cost, target, dp);
    }
    private String dfs(int[] cost, int target, String[] dp) {
        if (target<0) return "0";
        if (target==0) return "";
        if (dp[target]!=null) return dp[target];
        String res = "0";
        for (int i = 9; i>0; i--) {
            String rest = dfs(cost, target-cost[i-1], dp);
            if (rest.equals("0")) continue;
            String combined = i+rest;
            if (res.equals("0")|| combined.length()>res.length()) {
                res = combined;
            }
        }
        return dp[target]=res;
    }
}