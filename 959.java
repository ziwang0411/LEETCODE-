class Solution {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        DSU dsu = new DSU(4*n*n);
        for (int r = 0; r<n; r++) {
            for (int c = 0; c<n; c++) {
                int root = 4*(r*n+c);
                char val = grid[r].charAt(c);
                if (val!='\\') {
                    dsu.union(root+0, root+1);
                    dsu.union(root+2, root+3);
                }
                if (val!='/') {
                    dsu.union(root+0, root+2);
                    dsu.union(root+1, root+3);
                }
                
                if (r+1<n) {
                    dsu.union(root+3, (root+4*n)+0);
                }
                if (r-1>=0) {
                    dsu.union(root+0, (root-4*n)+3);
                }
                if (c+1<n) {
                    dsu.union(root+2, (root+4)+1);
                }
                if (c-1>=0) {
                    dsu.union(root+1, (root-4)+2);
                }
            }
        }
        int res = 0;
        for (int i = 0; i<4*n*n; i++) {
            if (dsu.find(i)==i) res++;
        }
        return res;
        
    }
}

class DSU {
    int[] parent;
    public DSU(int n) {
        parent = new int[n];
        for (int i = 0; i<n; i++) parent[i]=i;
    }
    public int find(int x) {
        if (parent[x]!=x) parent[x] = find(parent[x]);
        return parent[x];
    }
    public void union(int x, int y) {
        parent[find(x)]=find(y);
    }
}