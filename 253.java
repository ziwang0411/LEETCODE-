class Solution {
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Integer> startTime = new PriorityQueue<>();
        PriorityQueue<Integer> endTime = new PriorityQueue<>();
        for (int[] interval: intervals) {
            startTime.offer(interval[0]);
            endTime.offer(interval[1]);
        }
        int min = Integer.MIN_VALUE;
        int currentRooms = 0;
        while (!startTime.isEmpty() && !endTime.isEmpty()) {
            if (startTime.peek()<endTime.peek()) {
                currentRooms++;
                startTime.poll();
            } else if (startTime.peek()>endTime.peek()) {
                currentRooms--;
                endTime.poll();
            } else {
                startTime.poll();
                endTime.poll();
            }
            min = Math.max(min, currentRooms);
        }
        return min;
    }
}