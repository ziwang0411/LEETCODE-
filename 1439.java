class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int col = Math.min(mat[0].length, k);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.add(0);
        for (int[] row : mat) {
            PriorityQueue<Integer> nextPq = new PriorityQueue<>((a, b) -> (b - a));
            for (int i : pq) {
                for (int c = 0; c < col; c++) {
                    nextPq.add(i + row[c]);
                    if (nextPq.size() > k) {
                        nextPq.poll();
                    }
                }
            }
            pq = nextPq;
        }
        return pq.poll();
    }
}

class Solution {
    int m;
    int n;
    int[][] mat;

    public int kthSmallest(int[][] mat, int k) {
        m = mat.length;
        n = mat[0].length;
        this.mat = mat;
        int l = m, r = m * 5000, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int count = countArraysHaveSumLessOrEqual(0, 0, mid, k);
            if (count >= k) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    private int countArraysHaveSumLessOrEqual(int row, int sum, int target, int k) {
        if (sum > target)
            return 0;
        if (row == m)
            return 1;
        int res = 0;
        for (int c = 0; c < n; c++) {
            int count = countArraysHaveSumLessOrEqual(row + 1, sum + mat[row][c], target, k - res);
            if (count == 0)
                break;
            res += count;
            if (res > k)
                break;
        }
        return res;
    }
}