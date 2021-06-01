class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        if (hour<(double)(dist.length-1)) return -1;
        int max = 10000000;
        int l = 1, r = max;
        while (l<=r) {
            int mid = l+(r-l)/2;
            if( isValid(dist, mid, hour)) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        } 
        return l>max? -1 : l;
    }
    private boolean isValid(int[] dist, int speed, double hour) {
        double total = 0.0;
        for (int i = 0; i<dist.length; i++) {
            int d = dist[i];
            if (d%speed==0) {
                total+=(1.0*d)/speed;
            } else {
                if (i==dist.length-1) {
                    total+=(1.0*d)/speed;
                }else total+=1.0*(d/speed+1);
            }
        }
        // System.out.println(speed+" , "+ total);
        return total<=hour;
    }
}