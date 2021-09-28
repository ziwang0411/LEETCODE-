class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        int m = mat.length;
        Integer[][] dp = new Integer[m][5001];
        return search(mat, dp, target, 0, 0);
    }
    private int search(int[][] mat, Integer[][] dp, int target, int row, int sum) {
        if (row==mat.length) {
            return Math.abs(target-sum);
        }
        if (dp[row][sum]!=null) {
            return dp[row][sum];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i<mat[row].length; i++) {
            res = Math.min(res, search(mat, dp, target, row+1, sum+mat[row][i]));
        }
        return dp[row][sum]=res;
    }
}