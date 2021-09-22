class Solution {
    int result;
    int[] toppingCosts;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        result = baseCosts[0];
        this.toppingCosts = toppingCosts;
        for (int base : baseCosts) {
            calculate(base, target, 0);
        }
        return result;
    }

    private void calculate(int currCost, int target, int index) {
        if (Math.abs(target - currCost) < Math.abs(target - result)
                || Math.abs(target - currCost) == Math.abs(target - result) && currCost < result)
            result = currCost;
        // if (currCost>=result || index==toppingCosts.length) return;
        if (index == toppingCosts.length || currCost >= target)
            return;

        calculate(currCost, target, index + 1);
        calculate(currCost + toppingCosts[index], target, index + 1);
        calculate(currCost + 2 * toppingCosts[index], target, index + 1);
    }
}