class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind uf = new UnionFind(n);
        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i<n; i++) {
                int key = uf.find(i);
                map.putIfAbsent(key, new HashMap<Integer, Integer>());
                Map<Integer, Integer> count = map.get(key);
                count.put(source[i], count.getOrDefault(source[i], 0)+1);
        }
        int res = 0;
        for (int i = 0; i<n; i++) {
            int num = target[i];
            int root = uf.find(i);
            Map<Integer, Integer> count = map.get(root);
            if (count.getOrDefault(num, 0)==0) res++;
            else count.put(num, count.get(num)-1);
        }
        return res;
    }
}
class UnionFind {
    int[] parent;
    int[] rank;
    int n;
    public UnionFind(int n) {
        this.n=n;
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
            if (rank[px]==rank[py]) rank[px]+=1;
        }
    }
}