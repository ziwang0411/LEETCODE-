class Solution {
    Map<Integer, List<Integer>> graph;
    List<List<int[]>> path;
    int[] res;
    public int[] getCoprimes(int[] nums, int[][] edges) {
        graph = new HashMap<>();
        for (int[] edge: edges) {
            int u = edge[0], v = edge[1];
            graph.putIfAbsent(u, new ArrayList<Integer>());
            graph.putIfAbsent(v, new ArrayList<Integer>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        path = new ArrayList<>();
        for (int i = 0; i<51; i++) path.add(new ArrayList<int[]>());
        res = new int[nums.length];
        Arrays.fill(res, -1);
        dfs(nums, 0, 0, 0);
        return res;
    }
    
    private void dfs(int[] nums, int node, int depth, int parent) {
        int largestDepth = -1;
        for (int x = 0; x<51; x++) {
            if (gcd(nums[node], x)==1) {
                if (path.get(x).size()>0) {
                    int[] top = path.get(x).get(path.get(x).size()-1); // node number, depth
                    if (largestDepth<top[1]) {
                        res[node] = top[0];
                        largestDepth = top[1];
                    }
                }
            }
        }
        if (!graph.containsKey(node)) return;
        path.get(nums[node]).add(new int[]{node, depth});
        for (int next : graph.get(node)) {
            if (next==parent) continue;
            dfs(nums, next, depth+1, node);
        }
        path.get(nums[node]).remove(path.get(nums[node]).size()-1);

    }
    
    private int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b, a%b);
    }
}