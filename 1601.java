class Solution {
    int max = 0;

    public int maximumRequests(int n, int[][] requests) {
        helper(requests, new int[n], 0, 0);
        return max;
    }

    private void helper(int[][] requests, int[] count, int index, int num) {
        if (index == requests.length) {
            for (int v : count) {
                if (v != 0)
                    return;
            }
            max = Math.max(max, num);
            return;
        }
        int[] req = requests[index];
        if (req[0] == req[1]) {
            helper(requests, count, index + 1, num + 1);
        } else {
            count[req[0]]--;
            count[req[1]]++;
            helper(requests, count, index + 1, num + 1);
            count[req[0]]++;
            count[req[1]]--;
            helper(requests, count, index + 1, num);
        }
    }
}