class Solution {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] road: roads) {
            int from = road[0], to = road[1];
            if (!map.containsKey(from)) map.put(from, new ArrayList<>());
            map.get(from).add(to);
            if (!map.containsKey(to)) map.put(to, new ArrayList<>());
            map.get(to).add(from);
        }
        int[][] dist = new int[n][targetPath.length];
        int[][] parents = new int[n][targetPath.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            int distA = dist[a[0]][a[1]];
            int distB = dist[b[0]][b[1]];
            if (distA==distB) return b[1]-a[1];
            return distA-distB;
        });
        for (int i = 0; i<n; i++) {
            dist[i][0] = targetPath[0].equals(names[i])? 0 : 1;
            pq.add(new int[] {i,0});
            for (int j = 1; j<targetPath.length; j++) dist[i][j] = Integer.MAX_VALUE;
        }
        int lastCity = -1;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (curr[1]==targetPath.length-1) {
                lastCity = curr[0];
                break;
            }
            int i = curr[0], j = curr[1];
            int d = dist[i][j];
            for (Integer next : map.get(i)) {
                int nextDistance = d+(targetPath[j+1].equals(names[next])?0:1);
                if (nextDistance<dist[next][j+1]) {
                    dist[next][j+1] = nextDistance;
                    parents[next][j+1] = i;
                    pq.add(new int[] {next, j+1});
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = targetPath.length-1; i>=0; i--) {
            res.add(lastCity);
            lastCity = parents[lastCity][i];
        }
        Collections.reverse(res);
        return res;
        
    }
}