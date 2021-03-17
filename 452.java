class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length==0) return 0;
        Arrays.sort(points, (int[] a, int[] b)-> {
            if (a[0]<=0 && b[0]>=0) return -1;
            if (a[0]>=0 && b[0]<=0) return 1;
            return a[0]-b[0];
        });
        int arrows = 1;
        int end = points[0][1];
        for (int[] point: points) {
            if (point[0]<=end) {
                end = Math.min(end, point[1]);
            } else {
                end = point[1];
                arrows++;
            }
        }
        return arrows;
        
    }
}