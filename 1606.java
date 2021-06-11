class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a,b)->(a[0]-b[0]));
        int[] counts = new int[k];
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i<k; i++) {
            availableServers.add(i);
        }
        int maxNum = 0;
        for (int i = 0; i<arrival.length; i++) {
            int startTime = arrival[i];
            int duration = load[i];
            while (!busyServers.isEmpty() && busyServers.peek()[0]<=startTime) {
                int[] freedServer = busyServers.poll();
                availableServers.add(freedServer[1]);
            }
            if (availableServers.size()==0) continue;
            Integer serverNum = availableServers.ceiling(i%k);
            if (serverNum==null) serverNum = availableServers.first();
            availableServers.remove(serverNum);
            counts[serverNum]++;
            maxNum = Math.max(maxNum, counts[serverNum]);
            busyServers.add(new int[] {startTime+duration, serverNum});
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i<k; i++) {
            if (counts[i]==maxNum) res.add(i);
        }
        return res;
    }
}