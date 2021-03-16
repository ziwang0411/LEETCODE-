class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int[] prev = new int[] {-1,-1};
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0]!=b[0]) {
                    return a[0]-b[0];
                } else {
                    return b[1]-a[1];
                }
            }
        });
        int removed = 0;
        for (int[] interval: intervals) {
            if (prev[1]>interval[0]) {
                if (prev[1]>=interval[1]){
                    removed++;
                    continue;
                }
            }
            prev = interval;
        }
        return intervals.length-removed;
    }
}