class Solution {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        int i = 0, j = 0;
        int firstCounter = encoded1[i][1], secondCounter = encoded2[j][1];
        List<List<Integer>> res = new ArrayList<>();
        int prevVal = -1;
        int prevCount = 0;
        while (i<encoded1.length && j<encoded2.length) {
            int val = encoded1[i][0]*encoded2[j][0];
            if (val!=prevVal) {
                res.add(Arrays.asList(new Integer[] {prevVal, prevCount}));
                prevCount = 0;
            }
            prevVal = val;
            int count = Math.min(firstCounter, secondCounter);
            prevCount+=count;
            firstCounter-=count;
            secondCounter-=count;
            if (firstCounter==0) {
                i++;
                if (i<encoded1.length) firstCounter= encoded1[i][1];
            }
            if (secondCounter==0) {
                j++;
                if (j<encoded2.length) secondCounter = encoded2[j][1];
            }
        }
        res.add(Arrays.asList(new Integer[] {prevVal, prevCount}));
        res.remove(0);
        return res;
    }
}