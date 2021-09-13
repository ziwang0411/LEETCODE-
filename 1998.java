class Solution {
    public boolean gcdSort(int[] nums) {
        int n = nums.length;
        int maxNum = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        for (int i: nums) {
            maxNum = Math.max(maxNum, i);
            set.add(i);
        }
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        UnionFind uf = new UnionFind(maxNum+1);
        boolean[] notPrimes = new boolean[maxNum+1];
        for (int i = 2; i<=maxNum; i++) {
            if (notPrimes[i]) continue;
            for (int j = 2; i*j<=maxNum; j++) {
                notPrimes[i*j]=true;
                if (set.contains(i*j)) uf.union(i, i*j);
            }
        }
        for (int i = 0; i<n; i++) {
            if (uf.find(nums[i])!=uf.find(sorted[i])) return false;
        }
        return true;
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
}