class Solution {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[m][n][target+1];
        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                Arrays.fill(dp[i][j], 100000000);
            }
        }
        for (int j=0; j<n; j++) {
            if (houses[0]==j+1) dp[0][j][1]=0;
            else if (houses[0]==0) dp[0][j][1]=cost[0][j];
        }
        for (int i = 1; i<m; i++) {
            for (int k = 1; k<=Math.min(target, i+1); k++) {
                for (int j = 0; j<n; j++) {
                    if (houses[i]!=0 && houses[i]!=j+1) continue;
                    int same = dp[i-1][j][k];
                    int diff = 100000000;
                    for (int c=0; c<n; c++) {
                        if (c!=j) {
                            diff = Math.min(diff, dp[i-1][c][k-1]);
                        }
                    }
                    int paintCost = houses[i]==j+1?0:cost[i][j];
                    dp[i][j][k]=Math.min(same, diff)+paintCost;
                }
            }
        }
        int res = 100000000;
        for (int j = 0; j<n; j++) {
            res=Math.min(res, dp[m-1][j][target]);
        }
        if (res==100000000) return -1;
        return res;
    }
}