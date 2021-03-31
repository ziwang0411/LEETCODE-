class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m*k>bloomDay.length) return -1;
        
        int l =1, r = 1;
        for (int b: bloomDay) {
            if (b>r) r = b; 
        }
        while (l<=r) {
            int mid = l+(r-l)/2;
            int bouquets = find(bloomDay, mid, k);
            if (bouquets<m) l = mid+1;
            else r = mid-1;
        }
        return l;
    }
    private int find(int[] bloomDay, int day, int k) {
        int res = 0;
        for (int i = 0; i<bloomDay.length; i+=k) {
            int j = 0;
            for (; j<k && i+j<bloomDay.length; j++) {
                int index = i+j;
                if(bloomDay[index]>day) {
                    i = index+1-k;
                    break;
                }
            }
            if (j==k) res++; 
        }
        return res;
    }
}