class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int l = 1, r = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int days = calculate(weights, mid);
            if (days > D) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private int calculate(int[] weights, int capacity) {
        int count = 1;
        int available = capacity;
        for (int w : weights) {
            if (w > capacity)
                return Integer.MAX_VALUE;
            if (available >= w) {
                available -= w;
            } else {
                count++;
                available = capacity;
                available -= w;
            }
        }
        // System.out.println(count+" , "+ capacity);
        return count;
    }
}