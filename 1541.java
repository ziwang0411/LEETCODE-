class Solution {
    public int minInsertions(String s) {
        int res = 0;
        int right = 0;
        for (int i = 0; i< s.length(); i++) {
            char c = s.charAt(i);
            if (c=='(') {
                if (right%2==1) {
                    right--;
                    res++;
                }
                right+=2;
            } else {
                right--;
                if (right<0) {
                    right+=2;
                    res++;
                }
            }
        }
        return right+res;
    }
}