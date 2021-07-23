class Solution {
    int[] parent;
    int[] relatedBricks;
    int[] rank;
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m*n+1];
        for (int i =0; i<m*n+1; i++) parent[i]=i;
        relatedBricks = new int[m*n+1];
        rank = new int[m*n+1];
        int[][] gridCopy = new int[m][n];
        
        for (int r = 0; r < m; ++r)
            gridCopy[r] = grid[r].clone();
        int[][] dirs = {{0,-1},{-1,0},{0,1},{1,0}};

        for (int[] hit : hits) {
            gridCopy[hit[0]][hit[1]] = 0;
            relatedBricks[hit[0]*n+hit[1]]=1;

        }
        
        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                if (gridCopy[i][j]==1) {
                    relatedBricks[i*n+j]=1;
                    if (i==0) union(m*n, i*n+j);
                    if (i>0 && gridCopy[i-1][j]==1) {
                        union(i*n+j, (i-1)*n+j);
                    }
                    if (j>0 && gridCopy[i][j-1]==1) {
                        union(i*n+j, i*n+j-1);
                    }
                }
            }
        }
        
        
        int[] res = new int[hits.length];
        
        for (int index = hits.length-1; index>=0; index--) {
            int[] hit = hits[index];
            int prev = relatedBricks[find(m*n)];
            if (grid[hit[0]][hit[1]]==0) continue;
            for (int[] dir: dirs) {
                int nr = hit[0]+dir[0], nc=hit[1]+dir[1];
                if (nr>=0 && nr<m && nc>=0 && nc<n && gridCopy[nr][nc]==1) {
                    union(hit[0]*n+hit[1],nr*n+nc);
                }
            }
            if (hit[0]==0) union(m*n, hit[0]*n+hit[1]);
            gridCopy[hit[0]][hit[1]] = 1;
            res[index]=Math.max(0, relatedBricks[find(m*n)]-prev-1);
            
        }
        return res;
        
    }
    private void union(int x, int y) {
        int parentX = find(x), parentY = find(y);
        if (parentX==parentY) return;
        if (rank[parentX]<rank[parentY]) {
            int temp = parentY;
            parentY=parentX;
            parentX = temp;
        }
        if (rank[parentX]==rank[parentY]) rank[parentX]++;
        
        parent[parentY] = parentX;
        relatedBricks[parentX]+=relatedBricks[parentY];
    }
    private int find(int x) {
        // int[] temp = relatedBricks;
        //         int[] parent1 = parent;
        if (parent[x]!=x) parent[x]=find(parent[x]);
        return parent[x];
    }
}
