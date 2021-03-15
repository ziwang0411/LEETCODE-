class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0]-b[0];
            }
        });
        int result = 0;
        int[] prev = new int[]{Integer.MIN_VALUE,Integer.MIN_VALUE};
        for (int[] interval: intervals) {
            if (interval[0]>=prev[1]) {
                prev = interval;
                continue;
            }
            if (interval[1]<prev[1]) {
                prev = interval;
                result++;
                continue;
            } else {
                result++;
            }
        }
        return result;
    }
}