class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int left = 0;
        int n = arr.length;
        while (left < n - 1 && arr[left] <= arr[left + 1])
            left++;
        if (left == n - 1)
            return 0;

        int right = n - 1;
        while (right >= 1 && arr[right] >= arr[right - 1])
            right--;
        int minLength = Math.min(right, n - left - 1);

        int i = 0, j = right;
        while (i <= left && j < n) {
            if (arr[i] <= arr[j]) {
                minLength = Math.min(minLength, j - i - 1);
                i++;
            } else {
                j++;
            }
        }
        return minLength;
    }
}