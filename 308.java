class NumMatrix {
    int[][] tree;
    int m;
    int n;
    public NumMatrix(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        tree = new int[m][4*n];
        for (int i = 0; i<m; i++) {
            buildTree(matrix[i], i, 1, 0, n-1);
        }
    }
    
    public void update(int row, int col, int val) {
        updateTree(row, col, 1, 0, n-1, val);
    }
    
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i<=row2; i++) {
            sum+=calculateSum(i,1, 0, n-1, col1, col2);
        }
        return sum;
    }
    
    private int calculateSum(int treeRow, int root, int leftBound, int rightBound, int l, int r) {
        if (l>r) return 0;
        if (l==leftBound && r ==rightBound) return tree[treeRow][root];
        
        int midBound = leftBound+(rightBound-leftBound)/2;
        return calculateSum(treeRow, root*2, leftBound, midBound, l, Math.min(midBound, r))+calculateSum(treeRow, root*2+1, midBound+1, rightBound, Math.max(l, midBound+1), r);
    }
    
    private void updateTree(int treeRow, int index, int root, int l, int r, int val) {
        if (l==r) {
            tree[treeRow][root]= val;
        } else {
            int mid = l+(r-l)/2;
            if (index<=mid) {
                updateTree(treeRow, index, root*2, l, mid, val);
            } else {
                updateTree(treeRow, index, root*2+1, mid+1, r, val);
            }
            tree[treeRow][root] = tree[treeRow][root*2]+tree[treeRow][root*2+1];
        }
    }
    private void buildTree(int[] nums, int treeRow, int root, int l, int r) {
        if (l==r) {
            tree[treeRow][root] = nums[l];
        } else {
            int mid = l+(r-l)/2;
            buildTree(nums, treeRow, root*2, l, mid);
            buildTree(nums, treeRow, root*2+1, mid+1, r);
            tree[treeRow][root] = tree[treeRow][root*2]+tree[treeRow][root*2+1];
        }
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */