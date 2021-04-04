class Solution {
    public int maximizeSweetness(int[] sweetness, int K) {
        int l = Integer.MAX_VALUE, r = 0;
        for (int s:sweetness) {
            l = Math.min(l, s);
            r+=s;
        }
        while (l<=r) {
            int mid = l+(r-l)/2;
            int pieces = cut(sweetness, mid);
            if (pieces>=K+1) {
                l= mid+1;
            } else {
                r = mid-1;
            }
        }
        return r;
    }
    private int cut(int[] sweetness, int capacity) {
        int res = 0;
        int available = capacity;
        for (int s: sweetness) {
            if (available<=s) {
                res++;
                available=capacity;
            } else {
                available-=s;
            }
        }
        return res;
    }
}