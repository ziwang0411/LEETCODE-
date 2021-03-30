class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = 1;
        for (int p: piles) {
            r = Math.max(r, p+1);
        }
        while (l<=r) {
            int mid = l+(r-l)/2;
            int hoursTaken = 0;
            for (int i = 0; i<piles.length; i++) {
                hoursTaken+=1+(piles[i]-1)/mid;
            }
            if (hoursTaken>h) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return l;
    }
}