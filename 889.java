class Solution {
    Map<Integer, Integer> index_post = new HashMap();
    Map<Integer, Integer> index_pre = new HashMap();

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        for (int i = 0; i < pre.length; i++) {
            index_pre.put(pre[i], i);
            index_post.put(post[i], i);
        }
        return buildTree(0, pre.length - 1, pre, post);
    }

    public TreeNode buildTree(int preStart, int preEnd, int[] pre, int[] post) {
        if (preStart > preEnd)
            return null;
        if (preStart == preEnd)
            return new TreeNode(pre[preStart]);
        TreeNode curr = new TreeNode(pre[preStart]);
        int index = index_pre.get(post[index_post.get(pre[preStart]) - 1]);
        curr.left = buildTree(preStart + 1, index - 1, pre, post);
        curr.right = buildTree(index, preEnd, pre, post);
        return curr;
    }
}