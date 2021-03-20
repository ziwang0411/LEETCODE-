class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        PriorityQueue<Event> pq = new PriorityQueue<Event>(new Comparator<Event>() {
            public int compare(Event a, Event b) {
                if (a.time==b.time) return a.rect[1]-b.rect[1];
                return a.time-b.time;
            }
        });
        int[] border = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int[] rect: rectangles) {
            Event open = new Event(rect[1], rect);
            Event close = new Event(rect[3], rect);
            pq.add(open);
            pq.add(close);
            if (rect[0]<border[0]) border[0] = rect[0];
            if (rect[2]>border[1]) border[1] = rect[2];
        }
        TreeSet<int[]> set = new TreeSet<int[]> (new Comparator<int[]> () {
            @Override
            public int compare(int[] rect1, int[] rect2) {
                if (rect1[2]<=rect2[0]) return -1;
                else if (rect2[2]<=rect1[0]) return 1;
                else return 0;
            }
        });
        int xLength = 0;
        while (!pq.isEmpty()) {
            int time = pq.peek().time;
            while (!pq.isEmpty() && pq.peek().time == time) {
                //pick all the lines at the same y value
                Event e = pq.poll();
                int[] rect = e.rect;
                if (time == rect[3]) { //closing event
                    set.remove(rect);
                    xLength -=rect[2]-rect[0];
                } else {
                    if (!set.add(rect)) return false;
                    xLength +=rect[2]-rect[0];
                }
            }
            if (!pq.isEmpty() && xLength!=border[1]-border[0]) return false;
        }
        return true;
    }
}

class Event {
    int time;
    int[] rect;
    
    public Event(int time, int[] rect) {
        this.time = time;
        this.rect = rect;
    }
}