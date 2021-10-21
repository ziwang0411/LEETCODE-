class Solution {
    int finish;
    Integer[][] memo;
    int mod;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        memo = new Integer[locations.length][fuel + 1];
        this.finish = finish;
        mod = 1_000_000_007;
        return search(locations, start, fuel);
    }

    private int search(int[] locations, int curr, int fuel) {
        if (fuel < 0)
            return 0;
        int res = 0;
        if (memo[curr][fuel] != null)
            return memo[curr][fuel];
        if (curr == finish)
            res += 1;
        for (int next = 0; next < locations.length; next++) {
            if (next == curr)
                continue;
            int consumedFuel = Math.abs(locations[next] - locations[curr]);
            if (consumedFuel <= fuel) {
                res += search(locations, next, fuel - consumedFuel) % mod;
                res %= mod;
            }
        }
        memo[curr][fuel] = res % mod;
        return memo[curr][fuel];
    }
}