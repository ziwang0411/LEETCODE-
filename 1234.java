class Solution {
    public int balancedString(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        if (isValid(map, s.length()/4)) return 0;
        int l = 0, r = 0;
        int minLength = s.length()+1;
        while (r<s.length()) {
            char rc = s.charAt(r);
            map.put(rc, map.get(rc)-1);
            while (l<=r && isValid(map, s.length()/4)) {
                minLength = Math.min(minLength, r-l+1);
                char lc = s.charAt(l);
                map.put(lc, map.get(lc)+1);
                l++;
            }
            r++;

        }
        return minLength;
    }
    private boolean isValid(HashMap<Character, Integer> map, int n) {
        for (char c: map.keySet()) {
            if (map.get(c)>n) return false;
        }
        return true;
    }
}