class Solution {
    int[] position;

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        this.position = position;
        int l = 1, r = position[position.length - 1] - position[0];
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isValid(mid, m)) {
                l = mid + 1;
            } else
                r = mid - 1;
        }
        return r;
    }

    private boolean isValid(int dist, int m) {
        int prev = 0;
        for (int i = 1, j = 1; i < m; i++) {
            while (j < position.length && position[j] < position[prev] + dist)
                j++;
            if (j >= position.length)
                return false;
            prev = j;
        }
        return true;
    }
}