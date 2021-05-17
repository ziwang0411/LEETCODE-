class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(0);
            } else {
                int curr = stack.pop();
                int prev = stack.pop();
                stack.push(prev + Math.max(2 * curr, 1));
            }
        }
        return stack.pop();
    }
}
// Divide and Conquer

class Solution {
    public int scoreOfParentheses(String s) {
        return calculate(s, 0, s.length() - 1);
    }

    private int calculate(String s, int start, int end) {
        int res = 0;
        int layers = 0;
        for (int i = start; i <= end; i++) {
            layers += s.charAt(i) == '(' ? 1 : -1;
            if (layers == 0) {
                if (i - start == 1)
                    res += 1;
                else
                    res += 2 * calculate(s, start + 1, i - 1);
                start = i + 1;
            }
        }
        return res;
    }
}