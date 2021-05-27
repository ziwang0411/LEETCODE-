class Solution {
    public int findTheLongestSubstring(String s) {
        int[] vowels = new int[]{'a'-'a', 'e'-'a', 'i'- 'a', 'o'-'a', 'u'-'a'};
        
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int mask = 0;
        for (int i = 0; i<s.length(); i++) {
            for (int v : vowels) {
                if (v==s.charAt(i)-'a') {
                    mask = mask^(1<<(s.charAt(i)-'a'));
                }
            }
            map.putIfAbsent(mask, i);
            res = Math.max(res, i-map.get(mask));
        }
        return res;
    }
}