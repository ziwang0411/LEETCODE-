class Solution {
    public int minCostII(int[][] costs) {
        int n = costs.length, k = costs[0].length;
        int minColor = -1, secondMinColor = -1;
        int[][] dp = new int[n][k];
        for (int i = 0; i<k; i++) {
            int price = costs[0][i];
            dp[0][i]=price;
            if (minColor==-1 || price<costs[0][minColor]) {
                secondMinColor = minColor;
                minColor = i;
            } else if (secondMinColor==-1|| price<costs[0][secondMinColor]) {
                secondMinColor = i;
            }
        }
        
        for (int i = 1; i<n; i++) {
            int nextMinColor = -1;
            int nextSecondMinColor = -1;
            for (int j = 0; j<k; j++) {
               if (j==minColor) {
                   dp[i][j]=dp[i-1][secondMinColor]+costs[i][j];
               } else {
                   dp[i][j]=dp[i-1][minColor]+costs[i][j];
               }
                if (nextMinColor==-1 || dp[i][j]<dp[i][nextMinColor]) {
                    nextSecondMinColor = nextMinColor;
                    nextMinColor = j;
                } else if (nextSecondMinColor==-1 || dp[i][j]<dp[i][nextSecondMinColor]) nextSecondMinColor=j;
            }
            minColor = nextMinColor;
            secondMinColor = nextSecondMinColor;
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i<k; i++) {
            res = Math.min(res, dp[n-1][i]);
        }
        return res;
    }
}