class Solution {
    public int[][] outerTrees(int[][] trees) {
        Arrays.sort(trees, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        Stack<int[]> stack = new Stack<>();
        int n = trees.length;
        for (int i = 0; i < n; i++) {
            while (stack.size() >= 2
                    && orientation(stack.get(stack.size() - 2), stack.get(stack.size() - 1), trees[i]) > 0)
                stack.pop();
            stack.push(trees[i]);
        }
        // stack.pop();
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() >= 2
                    && orientation(stack.get(stack.size() - 2), stack.get(stack.size() - 1), trees[i]) > 0)
                stack.pop();
            stack.push(trees[i]);
        }
        HashSet<int[]> ret = new HashSet<>(stack);
        return ret.toArray(new int[ret.size()][]);
    }

    public int orientation(int[] p, int[] q, int[] r) {
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
    }
}