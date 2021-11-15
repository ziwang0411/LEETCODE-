class Solution {
    int earlist = Integer.MAX_VALUE;
    int latest = Integer.MIN_VALUE;
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        dfs((1<<n)-1, 1, 0, 27, Math.min(firstPlayer, secondPlayer)-1, Math.max(firstPlayer, secondPlayer)-1);
        return new int[] {earlist, latest};
    }
    
    private void dfs(int mask, int round, int i, int j , int first, int second) {
        if (i>=j) {
            dfs(mask, round+1, 0, 27, first, second);
        } else if ((mask & (1<<i))==0) {
            dfs(mask, round, i+1, j, first, second);
        } else if ((mask & (1<<j))==0) {
            dfs(mask, round, i, j-1, first, second);
        } else if (i==first && j ==second) {
            earlist = Math.min(earlist, round);
            latest = Math.max(latest, round);
        }
        else {
            if (i!=first && i!=second) {
                dfs(mask^(1<<i), round, i+1, j-1, first, second);
            } if (j!=first && j!=second) {
                dfs(mask^(1<<j), round, i+1, j-1, first, second);
            }
        }
         
    }
}