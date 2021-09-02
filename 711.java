class Solution {
    int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    int[][] refl = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

    public int numDistinctIslands2(int[][] grid) {
        Set<String> set = new HashSet<>();
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    dfs(grid, visited, list, i, j);
                    set.add(normalize(list));
                    list.clear();
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, boolean[][] visited, List<int[]> list, int r, int c) {
        int m = grid.length, n = grid[0].length;
        list.add(new int[] { r, c });
        for (int[] dir : dirs) {
            int nr = r + dir[0], nc = c + dir[1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc] && grid[nr][nc] == 1) {
                visited[nr][nc] = true;
                dfs(grid, visited, list, nr, nc);
            }
        }
    }

    private String normalize(List<int[]> list) {
        List<List<int[]>> combinations = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            combinations.add(new ArrayList<int[]>());
        for (int i = 0; i < 4; i++) {
            for (int[] point : list) {
                combinations.get(i).add(new int[] { point[0] * refl[i][0], point[1] * refl[i][1] });
                combinations.get(i + 4).add(new int[] { point[1] * refl[i][1], point[0] * refl[i][0] });
            }
        }
        for (int i = 0; i < 8; i++) {
            Collections.sort(combinations.get(i), new MyComparator());
        }
        String[] s = new String[8];
        for (int i = 0; i < 8; i++) {
            StringBuilder sb = new StringBuilder();
            List<int[]> onelist = combinations.get(i);
            int x0 = onelist.get(0)[0], y0 = onelist.get(0)[1];
            for (int[] point : onelist) {
                sb.append(point[0] - x0);
                sb.append(",");
                sb.append(point[1] - y0);
                sb.append("!");
            }
            s[i] = sb.toString();
        }
        Arrays.sort(s);
        return s[0];
    }
}

class MyComparator implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        if (a[0] == b[0])
            return a[1] - b[1];
        return a[0] - b[0];
    }
}