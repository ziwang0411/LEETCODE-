class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0]-b[0];
            }
        });
        list.add(intervals[0]);
        int[] prev = intervals[0];
        for (int i =1; i<intervals.length; i++) {
            int[] current = intervals[i];
            //[1,5] and [2,3]
            if (prev[1]>=current[1]) continue;
            //[1,3] and [4,6]
            if (prev[1]<current[0]) {
                list.add(current);
                prev=current;
            } else {
             //[1,3] and [1,5]
                list.remove(list.size()-1);
                int[] merged = new int[] {prev[0], current[1]};
                list.add(merged);
                prev=merged;
            }
        }
        int[][] result = new int[list.size()][2];
        for (int i =0; i<result.length; i++) {
            result[i]=list.get(i);
        }
        return result;
    }
}