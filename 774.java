class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        int[] diff = new int[stations.length-1];
        int maxDiff = 0;
        for (int i = 1; i<stations.length; i++) {
            diff[i-1] = stations[i]-stations[i-1];
            maxDiff = Math.max(maxDiff, diff[i-1]); 
        }
        double l=0, r = 1.0*maxDiff;
        while (r-l>1e-6) {
            double mid = (l+r)/2.0;
            if (possible(mid, diff, k)) {
                r=mid;
            } else {
                l=mid;
            }
        }
        return l;
    }
    public boolean possible(double D, int[] diff, int k) {
        int used = 0;
        for (int d: diff) {
            used+=(int)(d/D);
        }
        return used<=k;
    }
}