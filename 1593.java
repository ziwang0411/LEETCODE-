class Solution {
    int res = 1;

    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        backtrack(s, 0, set);
        return res;
    }

    private void backtrack(String s, int start, Set<String> set) {
        if (start == s.length()) {
            res = Math.max(res, set.size());
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String substring = s.substring(start, i);
            if (!set.contains(substring)) {
                set.add(substring);
                backtrack(s, i, set);
                set.remove(substring);
            }
        }
    }
}