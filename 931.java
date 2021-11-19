class Solution {
    int m, n;

    public int minFallingPathSum(int[][] matrix) {
        this.m = matrix.length;
        this.n = matrix[0].length;
        Integer[][] dp = new Integer[m][n];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dfs(matrix, dp, 0, i));
        }
        return res;
    }

    private int dfs(int[][] matrix, Integer[][] dp, int row, int col) {
        if (row == m - 1)
            return matrix[row][col];
        if (dp[row][col] != null)
            return dp[row][col];
        int res = Integer.MAX_VALUE;
        if (col > 0)
            res = Math.min(res, matrix[row][col] + dfs(matrix, dp, row + 1, col - 1));
        res = Math.min(res, matrix[row][col] + dfs(matrix, dp, row + 1, col));
        if (col < n - 1)
            res = Math.min(res, matrix[row][col] + dfs(matrix, dp, row + 1, col + 1));
        dp[row][col] = res;
        return res;
    }
}