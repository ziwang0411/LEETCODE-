class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        if (n==0) return true;
        boolean[] visited = new boolean[n];
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int i = queue.poll();
            visited[i]=true;
            if (arr[i]==0) return true;
            if (i+arr[i]<=n-1 && !visited[i+arr[i]]) queue.add(i+arr[i]);
            if (i-arr[i]>=0 && !visited[i-arr[i]]) queue.add(i-arr[i]);
        }
        return false;
    }
}