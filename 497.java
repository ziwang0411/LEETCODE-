class Solution {
    int[][] rects;
    int[] areas;
    Random rand;

    public Solution(int[][] rects) {
        this.rects = rects;
        this.areas = new int[rects.length];
        for (int i = 0; i < rects.length; i++) {
            areas[i] = (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
        }
        rand = new Random();
    }

    public int[] pick() {
        int index = 0, areaCount = areas[0];
        for (int i = 1; i < rects.length; i++) {
            int possibility = rand.nextInt(areaCount + areas[i]) + 1;
            if (possibility > areaCount)
                index = i;
            areaCount += areas[i];
        }
        int[] rect = rects[index];
        int dx = rect[2] - rect[0], dy = rect[3] - rect[1];
        int resX = rect[0] + rand.nextInt(dx + 1);
        int resY = rect[1] + rand.nextInt(dy + 1);
        return new int[] { resX, resY };
    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(rects); int[] param_1 = obj.pick();
 */

class Solution {
    TreeMap<Integer, Integer> treeMap;
    int[][] rects;
    int areaSum;
    Random rand;

    public Solution(int[][] rects) {
        this.rects = rects;
        treeMap = new TreeMap<>();
        areaSum = 0;
        rand = new Random();
        for (int i = 0; i < rects.length; i++) {
            areaSum += (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
            treeMap.put(areaSum, i);
        }
    }

    public int[] pick() {
        int index = treeMap.ceilingKey(rand.nextInt(areaSum + 1));
        int[] rect = rects[treeMap.get(index)];
        int dx = rect[2] - rect[0], dy = rect[3] - rect[1];
        int resX = rect[0] + rand.nextInt(dx + 1);
        int resY = rect[1] + rand.nextInt(dy + 1);
        return new int[] { resX, resY };
    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(rects); int[] param_1 = obj.pick();
 */