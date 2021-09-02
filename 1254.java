class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i<m; i++) {
            if (grid[i][0]==0) {
                colorize(grid, i, 0);
            }
            if (grid[i][n-1]==0) {
                colorize(grid, i, n-1);
            }
        }
        for (int j = 0; j<n; j++) {
            if (grid[0][j]==0) {
                colorize(grid, 0, j);
            }
            if (grid[m-1][j]==0) {
                colorize(grid, m-1, j);
            }
        }
        int res = 0;
        for (int i = 1; i<m-1; i++) {
            for (int j = 1; j<n-1; j++) {
                if (grid[i][j]==0) {
                    colorize(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    private void colorize(int[][] grid, int row, int col) {
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        grid[row][col]=1;
        for (int[] dir: dirs) {
            int nr = row+dir[0], nc=col+dir[1];
            if (nr>=0 && nr<m && nc>=0 && nc<n && grid[nr][nc]==0) {
                colorize(grid, nr, nc);
            }
        }
    }
}