class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n==1) return 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i<arr.length; i++) {
            List<Integer> indexes = map.getOrDefault(arr[i], new ArrayList<Integer>());
            indexes.add(i);
            map.put(arr[i], indexes);
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        queue.offer(0);
        int step = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int t = 0; t < sz; t++) {
                int i = queue.poll();
                if (i==n-1) return step;
                List<Integer> indexes = map.get(arr[i]);
                for (int indexWithEqualValue: indexes) {
                    if (indexWithEqualValue==i || visited[indexWithEqualValue]) continue;
                    queue.offer(indexWithEqualValue);
                    visited[indexWithEqualValue] = true;
                }
                indexes.clear();
                if (i>0 && !visited[i-1]) {
                    queue.offer(i-1);
                    visited[i-1] = true;
                }
                if (i<n-1 && !visited[i+1]) {
                    queue.offer(i+1);
                    visited[i+1] = true;
                }
            }
            step++;
        }
        return step;
    }
}public class 1345 {
    
}
