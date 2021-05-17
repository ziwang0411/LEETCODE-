class Solution {
    public boolean checkValidString(String s) {
        int low = 0, high = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == '*') {
                high++;
                low--;
            } else {
                low--;
                high--;
                if (high < 0)
                    break;
            }
            low = Math.max(low, 0);
        }
        // System.out.println(low+" , "+high);
        return low == 0;
    }
}