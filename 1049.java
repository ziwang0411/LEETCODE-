class Solution {
    public int lastStoneWeightII(int[] stones) {
        Integer[][] memo = new Integer[30][6000];
        return search(memo, stones, 0, 0);
    }

    private int search(Integer[][] memo, int[] stones, int index, int sum) {
        if (index == stones.length) {
            return sum < 0 ? 3000 : sum;
        }
        if (memo[index][sum + 3000] != null)
            return memo[index][sum + 3000];
        memo[index][sum + 3000] = Math.min(search(memo, stones, index + 1, sum + stones[index]),
                search(memo, stones, index + 1, sum - stones[index]));
        return memo[index][sum + 3000];
    }
}