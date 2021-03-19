class Solution {
    public int rectangleArea(int[][] rectangles) {
        int OPEN = 0, CLOSE = 1;
        int[][] events = new int[rectangles.length*2][];
        int index = 0;
        for (int[] rec: rectangles) {
            events[index++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            events[index++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
        }
        Arrays.sort(events, (a,b) ->(a[0]-b[0]));
        
        List<int[]> active = new ArrayList<>();
        int currY = events[0][0];
        long ans = 0;
        for (int[] event: events) {
            int y = event[0], type = event[1], x1 = event[2], x2 = event[3];
            
            long query = 0;
            int curr = -1;
            for (int[] interval: active) {
                curr = Math.max(curr, interval[0]);
                query +=Math.max(interval[1]-curr, 0);
                curr = Math.max(curr, interval[1]);
            }
            ans+=query*(y-currY);
            currY = y;
            if (type==OPEN) {
                active.add(new int[] {x1, x2});
                Collections.sort(active, (a,b)->a[0]-b[0]);
            } else {
                for (int i = 0; i<active.size(); i++) {
                    if (active.get(i)[0]==x1 && active.get(i)[1]==x2) {
                        active.remove(i);
                        break;
                    }
                }
            }
        }
        ans%=1000000007;
        return (int) ans;
    }
}