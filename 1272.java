class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] interval: intervals) {
            if (interval[0]<toBeRemoved[0]) {
                if (interval[1]<=toBeRemoved[0]) {
                    addList(result, interval[0], interval[1]);
                } else if (interval[1]<=toBeRemoved[1]) {
                    addList(result, interval[0], toBeRemoved[0]);
                } else {
                    addList(result, interval[0], toBeRemoved[0]);
                    addList(result, toBeRemoved[1], interval[1]);
               }
            } else if (interval[0]<=toBeRemoved[1]) {
                if (interval[1]>toBeRemoved[1]) {
                    addList(result, toBeRemoved[1], interval[1]);
                }
            } else {
                    addList(result, interval[0], interval[1]);

            }
        }
        return result;
    }
    private void addList(List<List<Integer>> result, int start, int end) {
        List<Integer> oneList = new ArrayList<>();
                    oneList.add(start);
                    oneList.add(end);
                    result.add(oneList);
    }
}