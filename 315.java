class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i<n; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        int range = max-min+1;
        int[] tree = new int[4*range];
        for (int i = n-1; i>=0; i--) {
            res.add(sumRange(tree, 1, 0, range-1, 0, nums[i]-min-1));
            update(tree, 1, 0, range-1, nums[i]-min, 1);
        }
        Collections.reverse(res);
        return res;
    }
    private void update(int[] tree, int root, int l, int r, int pos, int val) {
        if (l==r) {
            tree[root]+=val;
        } else {
            int mid = l+(r-l)/2;
            if (pos<=mid) update(tree, 2*root, l, mid, pos, val);
            else update(tree, 2*root+1, mid+1, r, pos, val);
            tree[root] = tree[root*2]+tree[root*2+1];
        }
    }
    private int sumRange(int[] tree, int root, int leftBound, int rightBound, int l, int r) {
        if (l>r) return 0;
        if (l==leftBound && r == rightBound) return tree[root];
        int mid = leftBound+(rightBound-leftBound)/2;
        return sumRange(tree, root*2, leftBound, mid, l, Math.min(r, mid))+sumRange(tree, root*2+1, mid+1, rightBound, Math.max(l, mid+1), r);
    }
}