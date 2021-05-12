class Solution {
    HashMap<Character, int[]> startEndIndex;
    public List<String> maxNumOfSubstrings(String s) {
        startEndIndex = new HashMap<>();
        for (int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (!startEndIndex.containsKey(c)) {
                startEndIndex.put(c, new int[] {i, i});
            } else {
                int[] arr = startEndIndex.get(c);
                arr[1] = i;
                startEndIndex.put(c, arr);
            }
        }
        List<String> validStrings = new ArrayList<>();
        for (char c: startEndIndex.keySet()) {
            int[] range = startEndIndex.get(c);
            int start = range[0], end = range[1];
            int i = start+1;
            for (; i<end; i++) {
                int[] rangeForI = startEndIndex.get(s.charAt(i));
                if (rangeForI[0]<start) break;
                end = Math.max(end, rangeForI[1]);
            }
            if (i<end) continue;
            validStrings.add(s.substring(start, end+1));
        }
        Collections.sort(validStrings, (a,b)->(a.length()-b.length()));
        List<String> res = new ArrayList<>();
        for (String str: validStrings) {
            boolean isValid = true;
            for (String r: res) {
                if (!check(s, str, r)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) res.add(str);
        }
        return res;
    }
    private boolean check(String s, String newStr, String oldStr) {
        int newStrStart = s.indexOf(newStr);
        int newStrEnd = newStrStart+newStr.length()-1;
        int oldStrStart = s.indexOf(oldStr);
        int oldStrEnd = oldStrStart+oldStr.length()-1;
        return newStrStart>oldStrEnd || newStrEnd < oldStrStart;
    }
}