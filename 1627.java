class Solution {
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        UnionFind uf = new UnionFind(n+1);
        boolean[] notPrimes = new boolean[n+1];
        for (int i = threshold+1; i<=n; i++) {
            if (notPrimes[i]) continue;
            for (int j = 2; i*j<=n; j++) {
                notPrimes[i*j]=true;
                uf.union(i, i*j);
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            res.add(uf.isConnected(q[0],q[1]));
        }
        return res;
    }
}
class UnionFind {
    int[] parent;
    int[] rank;
    public UnionFind(int n) {
        parent=new int[n];
        rank = new int[n];
        for (int i = 0; i<n; i++) {
            parent[i]=i;
        }
    }
    public int find(int x) {
        if (parent[x]!=x) parent[x]=find(parent[x]);
        return parent[x];
    }
    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (rank[px]>rank[py]) {
            parent[py]=px;
        } else {
            parent[px]=py;
            if (rank[px]==rank[py]) rank[py]++;
        }
    }
    public boolean isConnected(int x, int y) {
        return find(x)==find(y);
    }
}