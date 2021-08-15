class Solution {
    public int longestBeautifulSubstring(String word) {
        
        char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u', 'f'};
        int res = 0;
        for (int l = 0; l<word.length(); l++) {
            if (word.charAt(l)!='a') continue;
            int index = 0;
            int r = l+1;
            while (r<word.length() && (word.charAt(r)==vowels[index]|| word.charAt(r)==vowels[index+1])) {
                if (word.charAt(r)==vowels[index+1]) index++;
                r++;
            }
            if (index==4) res = Math.max(res, r-l);
            l = r-1;
        }
        return res;
    }
}