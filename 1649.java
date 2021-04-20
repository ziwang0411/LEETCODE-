class Solution {
    public int createSortedArray(int[] instructions) {
        int m = (int) 1e5+1;
        int[] tree = new int[m*2];
        long cost = 0;
        int MOD = 1_000_000_007;
        for (int x: instructions) {
            cost+=Math.min(query(tree, 0, x-1, m), query(tree, x+1, m-1, m));
            update(tree, x, 1, m);
        }
        return (int)(cost%MOD);
    }
    private int query(int[] tree, int left, int right, int m) {
        int result = 0;
        int l = left+m, r = right+m;
        while (l<=r) {
            if (l%2==1) {
                result+=tree[l];
                l++;
            } if (r%2==0) {
                result+=tree[r];
                r--;
            }
            l/=2;
            r/=2;
        }
        return result;
    }
    private void update(int[] tree, int index, int value, int m) {
        index+=m;
        tree[index] +=value;
        index/=2;

        while (index>0) {
            tree[index] = tree[index*2]+tree[(index*2)+1];
            index/=2;

        }
    }
}