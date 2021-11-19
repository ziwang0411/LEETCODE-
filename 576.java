class Solution {
    int m,n;
    int mod = 1_000_000_007;
    Integer[][][] dp;
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m=m;
        this.n=n;
        this.dp = new Integer[m][n][maxMove+1];
        return dfs(startRow, startColumn, maxMove);
    }
    private int dfs(int row, int col, int moveLeft) {
        if (row==m||row==-1||col==n||col==-1) return 1;
        if (moveLeft==0) return 0;
        if (dp[row][col][moveLeft]!=null) return dp[row][col][moveLeft];
        int res = 0;
        res = (res+dfs(row+1, col, moveLeft-1))%mod;
        res = (res+dfs(row-1, col, moveLeft-1))%mod;
        res = (res+dfs(row, col+1, moveLeft-1))%mod;
        res = (res+dfs(row, col-1, moveLeft-1))%mod;
        dp[row][col][moveLeft]=res;
        return res;
    }
}