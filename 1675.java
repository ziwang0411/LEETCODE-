class Solution {
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num % 2 == 0) {
                maxHeap.offer(num);
                min = Math.min(min, num);
            } else {
                maxHeap.offer(num * 2);
                min = Math.min(min, num * 2);
            }
        }
        int res = Integer.MAX_VALUE;

        while (!maxHeap.isEmpty()) {
            int curr = maxHeap.poll();
            res = Math.min(res, curr - min);
            if (curr % 2 == 0) {
                maxHeap.offer(curr / 2);
                min = Math.min(min, curr / 2);
            } else {
                break;
            }
        }
        return res;
    }
}