class Solution {
    Integer[][][] memo;
    int n;
    public int numberOfSets(int n, int k) {
        this.n = n;
        this.memo = new Integer[n][k+1][2];
        return dp(0, k, 1);
    }
    private int dp(int index, int k, int isStart) {
        if (k==0) return 1;
        if (index==n) return 0;
        if (memo[index][k][isStart]!=null) return memo[index][k][isStart];
        int res = dp(index+1, k, isStart);
        if (isStart==1) {
            res+=dp(index+1, k, 0);
        } else {
            res+=dp(index, k-1,1);
        }
        memo[index][k][isStart]=res%1_000_000_007;
        return memo[index][k][isStart];
    }
}