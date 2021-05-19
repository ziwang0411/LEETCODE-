class Solution {
    public String minRemoveToMakeValid(String s) {
        int leftP = 0, pairs = 0;
        for (int i = 0; i<s.length(); i++) {
            if (s.charAt(i)=='(') {
                leftP++;
            } else if (s.charAt(i)==')') {
                if (leftP>0) {
                    pairs++;
                    leftP--;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        leftP = 0;
        int rightP = 0;
        for (int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c=='(' && leftP<pairs) {
                leftP++;
                sb.append(c);
            } else if (c==')' && leftP>rightP && rightP<pairs) {
                rightP++;
                sb.append(c);
            } else {
                if (Character.isAlphabetic(c)) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}