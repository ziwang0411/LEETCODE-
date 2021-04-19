class NumArray {
    int[] tree;
    int n;
    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[4*n];
        buildTree(nums, 1, 0, n-1);
    }
    
    private void buildTree(int[] nums, int root, int l, int r) {
        if (l==r) {
            tree[root] = nums[l];
        } else {
            int mid = l+(r-l)/2;
            buildTree(nums, root*2, l, mid);
            buildTree(nums, root*2+1, mid+1, r);
            tree[root] = tree[root*2]+tree[root*2+1];
        }
    }
    
    public void update(int index, int val) {
        updateTree(1, 0, n-1, index, val);
    }
    private void updateTree(int root, int l, int r, int index, int val) {
        if (l==r) {
            tree[root] = val;
        } else {
            int mid = l+(r-l)/2;
            if (index<=mid) {
                updateTree(root*2, l, mid, index, val);
            } else {
                updateTree(root*2+1, mid+1, r, index, val);
            }
            tree[root] = tree[root*2]+tree[root*2+1];
        }
    }
    
    public int sumRange(int left, int right) {
        return calculateSum(1, 0, n-1, left, right);
    }
    private int calculateSum(int root, int leftBound, int rightBound, int l, int r) {
        if (l>r) return 0;
        if (l==leftBound && r==rightBound) return tree[root];
        
        int midBound = leftBound+(rightBound-leftBound)/2;
        return calculateSum(root*2, leftBound, midBound, l, Math.min(r, midBound))+
            calculateSum(root*2+1, midBound+1, rightBound, Math.max(l, midBound+1), r);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */