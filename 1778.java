/**
 * // This is the GridMaster's API interface. // You should not implement it, or
 * speculate about its implementation class GridMaster { boolean canMove(char
 * direction); void move(char direction); boolean isTarget(); }
 */

class Solution {
    private static final int BLOCKED = -1;
    private static final int UNEXPLORED = 0;
    private static final int PATH = 1;
    private static final int TARGET = 2;
    private static final int START = 3;
    Character[] dirChars = new Character[] { 'U', 'D', 'L', 'R' };
    int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    public int findShortestPath(GridMaster master) {
        int[][] grid = new int[1001][1001];
        grid[500][500] = START;
        int[] target = dfs(master, grid, 500, 500);
        if (target == null)
            return -1;
        return bfs(grid, 500, 500, target);
    }

    private int[] dfs(GridMaster master, int[][] grid, int x, int y) {
        if (master.isTarget()) {
            grid[x][y] = TARGET;
            return new int[] { x, y };
        }

        int[] res = null;
        for (int i = 0; i < 4; i++) {
            int nx = x + dirs[i][0], ny = y + dirs[i][1];
            if (grid[nx][ny] == UNEXPLORED) {
                if (master.canMove(dirChars[i])) {
                    master.move(dirChars[i]);
                    grid[nx][ny] = PATH;
                    int[] target = dfs(master, grid, nx, ny);
                    if (target != null)
                        res = target;
                    master.move(reverse(dirChars[i]));
                } else {
                    grid[nx][ny] = BLOCKED;
                }
            }
        }
        return res;
    }

    private int bfs(int[][] grid, int x, int y, int[] target) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { x, y });
        int res = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            while (sz-- > 0) {
                int[] point = queue.poll();
                if (point[0] == target[0] && point[1] == target[1])
                    return res;
                for (int i = 0; i < 4; i++) {
                    int nx = point[0] + dirs[i][0], ny = point[1] + dirs[i][1];
                    if (grid[nx][ny] == PATH) {
                        grid[nx][ny] = BLOCKED;
                        queue.add(new int[] { nx, ny });
                    }
                }
            }
            res++;
        }
        return res;
    }

    private Character reverse(char c) {
        switch (c) {
            case 'U':
                return 'D';
            case 'D':
                return 'U';
            case 'L':
                return 'R';
            case 'R':
                return 'L';
        }
        return null;

    }

}