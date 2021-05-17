class Solution {
    HashMap<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>();
        if (expression.length() == 0)
            return res;
        if (memo.containsKey(expression))
            return memo.get(expression);
        boolean hasOperators = false;

        for (int i = 0; i < expression.length(); i++) {
            if (isOperator(expression.charAt(i))) {
                hasOperators = true;
                List<Integer> leftWays = diffWaysToCompute(expression.substring(0, i));
                List<Integer> rightWays = diffWaysToCompute(expression.substring(i + 1));
                for (int leftItem : leftWays) {
                    for (int rightItem : rightWays) {
                        res.add(calculate(leftItem, expression.charAt(i), rightItem));
                    }
                }
            }
        }
        if (!hasOperators) {
            res.add(Integer.parseInt(expression));
        }
        memo.put(expression, res);
        return res;
    }

    private boolean isOperator(Character c) {
        return c == '+' || c == '-' || c == '*';
    }

    private int calculate(int a, char op, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return -1;
    }
}