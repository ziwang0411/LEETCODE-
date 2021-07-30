class Solution {
    public int scheduleCourse(int[][] courses) {
        int currentTotalTime = 0;
        Arrays.sort(courses, (a, b) -> (a[1] - b[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for (int[] course : courses) {
            int duration = course[0];
            int lastDay = course[1];
            // System.out.println(duration+currentTotalTime+" , "+ lastDay);
            if (duration + currentTotalTime <= lastDay) {
                currentTotalTime += duration;
                pq.offer(duration);
                continue;
            }
            if (!pq.isEmpty() && duration < pq.peek()) {
                currentTotalTime -= pq.poll();
                currentTotalTime += duration;
                pq.offer(duration);
            }
        }
        return pq.size();
    }
}