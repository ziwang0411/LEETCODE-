class Solution {
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] xors = new int[n+1];
        int xor = 0;
        for (int i = 1; i<n+1; i++) {
            xor = xor^arr[i-1];
            xors[i] = xor;
        }
        int res = 0;
        for (int i = 0; i<n+1; i++) {
            for (int j = i+1; j<n+1; j++) {
                if (xors[i]==xors[j]) {
                    res+= j-i-1;
                }
            }
        }
        return res;
    }
}