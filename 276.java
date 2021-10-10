class Solution {
    public int numWays(int n, int k) {
        if (n == 1)
            return k;
        int prevPostWays = k * k;
        int prevTwoPostWays = k;
        for (int i = 3; i <= n; i++) {
            int curr = (k - 1) * (prevPostWays + prevTwoPostWays);
            prevTwoPostWays = prevPostWays;
            prevPostWays = curr;
        }
        return prevPostWays;
    }
}