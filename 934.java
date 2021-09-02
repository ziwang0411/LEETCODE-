class Solution {
    int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public int shortestBridge(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean foundFirstIsland = false;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m && !foundFirstIsland; i++) {
            for (int j = 0; j < n && !foundFirstIsland; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[] { i, j });
                    colorize(grid, queue, visited, i, j);
                    foundFirstIsland = true;
                    break;
                }
            }
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int nr = curr[0] + dir[0], nc = curr[1] + dir[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                        if (grid[nr][nc] == 1)
                            return step;
                        queue.offer(new int[] { nr, nc });
                        visited[nr][nc] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private void colorize(int[][] grid, Queue<int[]> queue, boolean[][] visited, int row, int col) {
        visited[row][col] = true;
        int m = grid.length, n = grid[0].length;
        for (int[] dir : dirs) {
            int nr = row + dir[0], nc = col + dir[1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc] && grid[nr][nc] == 1) {
                queue.add(new int[] { nr, nc });
                colorize(grid, queue, visited, nr, nc);
            }
        }
    }
}