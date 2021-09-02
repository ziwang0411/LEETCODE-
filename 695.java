class Solution {
    int[] dr = new int[] { 1, -1, 0, 0 };
    int[] dc = new int[] { 0, 0, 1, -1 };
    int totalArea = 0;

    public int maxAreaOfIsland(int[][] grid) {
        int[] dr = new int[] { 1, -1, 0, 0 };
        int[] dc = new int[] { 0, 0, 1, -1 };
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int result = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    visited[r][c] = true;
                    dfs(grid, visited, r, c);
                    result = Math.max(result, totalArea);
                    totalArea = 0;
                }
            }
        }
        return result;
    }

    private void dfs(int[][] grid, boolean[][] visited, int r, int c) {
        int m = grid.length, n = grid[0].length;
        totalArea++;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1 && !visited[nr][nc]) {
                visited[nr][nc] = true;
                dfs(grid, visited, nr, nc);
            }
        }
    }
}