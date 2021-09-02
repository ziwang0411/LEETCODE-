class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        int[][] grid = new int[m][n];
        UnionFind uf = new UnionFind(m * n);
        int currIslands = 0;
        int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int[] pos : positions) {
            int r = pos[0], c = pos[1];
            if (grid[r][c] == 1) {
                res.add(currIslands);
                continue;
            }
            Set<Integer> parents = new HashSet<>();
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                    parents.add(uf.find(nr * n + nc));
                    uf.union(nr * n + nc, r * n + c);
                }
            }
            currIslands = currIslands + 1 - parents.size();
            res.add(currIslands);
            grid[r][c] = 1;
        }
        return res;
    }
}

class UnionFind {
    int[] parents;

    public UnionFind(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    public int find(int x) {
        if (parents[x] != x)
            parents[x] = find(parents[x]);
        return parents[x];
    }

    public void union(int x, int y) {
        int px = find(x), py = find(y);
        parents[py] = px;

    }
}