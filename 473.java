class Solution {
    int len;
    int[] sides;

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int m : matchsticks) {
            sum += m;
        }
        if (sum % 4 != 0)
            return false;
        len = sum / 4;

        sides = new int[4];
        Arrays.sort(matchsticks);
        return search(matchsticks, matchsticks.length - 1);
    }

    private boolean search(int[] nums, int start) {
        if (start == -1) {
            for (int side : sides) {
                if (side != len)
                    return false;
            }
            return true;
        }
        for (int i = 0; i < 4; i++) {
            if (sides[i] + nums[start] > len)
                continue;
            sides[i] += nums[start];
            if (search(nums, start - 1))
                return true;
            sides[i] -= nums[start];
        }
        return false;
    }
}