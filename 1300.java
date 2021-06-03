class Solution {
    public int findBestValue(int[] arr, int target) {
        int l = 0, r = 0;
        for (int a : arr) {
            r = Math.max(r, a);
        }
        r++;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (isSmaller(arr, mid, target))
                l = mid + 1;
            else
                r = mid;
        }
        int sumLeft = Math.abs(target - calculateSum(arr, l - 1));
        int sumRight = Math.abs(target - calculateSum(arr, l));

        return sumLeft <= sumRight ? l - 1 : l;
    }

    private boolean isSmaller(int[] arr, int k, int target) {
        int sum = calculateSum(arr, k);
        return sum < target;
    }

    private int calculateSum(int[] arr, int k) {
        int sum = 0;
        for (int a : arr) {
            sum += a > k ? k : a;
        }
        return sum;
    }
}