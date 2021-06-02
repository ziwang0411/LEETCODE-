class Solution {
    public int maxValue(int n, int index, int maxSum) {
        int canBeAdded = maxSum-n;
        int l= 0, r = canBeAdded;
        while (l<=r) {
            int mid = l+(r-l)/2;
            if (canFill(n, index, mid, canBeAdded)) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return l;
    }
    
    private boolean canFill(int n, int index, int peak, int maxSum) {
        long left = Math.max(0, peak-index);
        long right = Math.max(0, peak-(n-index-1));
        long sum = 0;
        sum+=(peak+left)*(peak-left+1)/2;
        sum+=(peak+right)*(peak-right+1)/2;
        sum-=peak;
        if (sum<=maxSum) return true;
        return false;
    }
}