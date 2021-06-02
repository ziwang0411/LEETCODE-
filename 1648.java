class Solution {
    TreeMap<Integer, Integer> map;

    public int maxProfit(int[] inventory, int orders) {
        map = new TreeMap<>((a, b) -> (b - a));
        int mod = 1_000_000_007;
        int l = 0, r = 0;
        for (int i : inventory) {
            r = Math.max(r, i);
            int count = map.getOrDefault(i, 0);
            map.put(i, count + 1);
        }
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isValid(mid, orders))
                l = mid + 1;
            else
                r = mid - 1;
        }
        long res = 0;
        for (int i : map.keySet()) {
            if (i <= l)
                break;
            orders -= map.get(i) * (i - l);
            res = (res + 1L * (i + l + 1) * (i - l) / 2 % mod * map.get(i) % mod) % mod;
        }
        if (orders > 0) {
            res = (res + 1L * l * orders % mod) % mod;
        }
        return (int) res;
    }

    private boolean isValid(int k, int orders) {
        long canBeSold = 0;
        for (int i : map.keySet()) {
            if (i < k)
                break;
            canBeSold += (i - k) * map.get(i);
            if (canBeSold >= orders)
                return true;
        }
        return canBeSold >= orders;
    }
}

class Solution {
    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        int mod = 1_000_000_007;
        long res = 0;
        int n = inventory.length - 1;
        long count = 1;
        while (orders > 0) {
            if (n > 0 && inventory[n] > inventory[n - 1] && orders >= count * (inventory[n] - inventory[n - 1])) {
                res += count * sumFromNtoX(inventory[n], inventory[n - 1]);
                orders -= count * (inventory[n] - inventory[n - 1]);
            } else if (n == 0 || inventory[n] > inventory[n - 1]) {
                long a = orders / count;
                res += count * sumFromNtoX(inventory[n], inventory[n] - a);
                long b = orders % count;
                res += b * (inventory[n] - a);
                orders = 0;
            }
            res %= mod;
            n--;
            count++;
        }
        return (int) res;
    }

    private long sumFromNtoX(long n, long x) {
        return (n - x) * (n + x + 1) / 2;
    }
}