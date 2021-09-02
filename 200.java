class Solution {

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int result = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    dfs(r, c, grid, visited);
                    result++;
                }
            }
        }
        return result;
    }

    private void dfs(int r, int c, char[][] grid, boolean[][] visited) {
        visited[r][c] = true;
        int[] dr = { -1, 1, 0, 0 };
        int[] dc = { 0, 0, -1, 1 };
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == '1' && !visited[nr][nc]) {
                dfs(nr, nc, grid, visited);
            }
        }
    }
}