class Solution {
    HashSet<String> validExpressions = new HashSet<>();
    int minRemoved;
    public List<String> removeInvalidParentheses(String s) {
        int lcount = 0, toRemove= 0;
        for (int i =0; i<s.length(); i++) {
            if (s.charAt(i)=='(') lcount++;
            else if (s.charAt(i)==')') {
                if (lcount==0) toRemove++;
                else lcount--;
            }
        }
        minRemoved = lcount+toRemove;
        recurse(s, 0,0,0,new StringBuilder(), 0);
        return new ArrayList<String>(validExpressions);
    }
    private void recurse(String s, int start, int leftCount, int rightCount, StringBuilder expression, int removedCount) {
        if (start==s.length()) {
            if (leftCount==rightCount) {
                if (removedCount==minRemoved) {
                    validExpressions.add(expression.toString());
                }
            }
        } else {
            char c = s.charAt(start);
            int length = expression.length();
            if (c!='(' && c!=')') {
                expression.append(c);
                recurse(s, start+1,leftCount, rightCount, expression, removedCount);
                expression.setLength(length);
            } else {
                recurse(s, start+1, leftCount, rightCount, expression, removedCount+1);
                expression.append(c);
                if (c=='(') recurse(s, start+1, leftCount+1, rightCount, expression, removedCount);
                else if (c==')' && leftCount>rightCount) recurse(s, start+1, leftCount, rightCount+1, expression, removedCount);
                expression.setLength(length);
            }
        }
    }
}