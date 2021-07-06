class Solution {
    int[] sums;
    int totalSum;
    Random rand;

    public Solution(int[] w) {
        sums = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            sums[i] = (i == 0 ? w[i] : sums[i - 1] + w[i]);
        }
        rand = new Random();
        totalSum = sums[sums.length - 1];
    }

    public int pickIndex() {
        int target = rand.nextInt(totalSum) + 1;
        int l = 0, r = sums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (sums[mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return l;
    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(w); int param_1 = obj.pickIndex();
 */