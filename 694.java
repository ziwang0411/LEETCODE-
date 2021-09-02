class Solution {
    private int[][] grid;
    private boolean[][] seen;
    private Set<Pair<Integer, Integer>> currentIsland;
    private int currentRowOrigin;
    private int currentColOrigin;

    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        this.seen = new boolean[grid.length][grid[0].length];
        Set<Set<Pair<Integer, Integer>>> islands = new HashSet<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                this.currentIsland = new HashSet<>();
                this.currentRowOrigin = row;
                this.currentColOrigin = col;
                dfs(row, col);
                if (!currentIsland.isEmpty())
                    islands.add(currentIsland);
            }
        }
        return islands.size();
    }

    private void dfs(int row, int col) {
        if (grid[row][col] == 0 || seen[row][col])
            return;
        int[] dr = { 1, -1, 0, 0 };
        int[] dc = { 0, 0, 1, -1 };
        seen[row][col] = true;
        currentIsland.add(new Pair<>(row - currentRowOrigin, col - currentColOrigin));
        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length) {
                dfs(nr, nc);
            }
        }

    }
}