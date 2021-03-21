class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
   List<List<Integer>> points = new ArrayList<>();
    List<List<Integer>> results = new ArrayList<>();
    int n = buildings.length;
    //求出将左上角和右上角坐标, 左上角坐标的 y 存负数
    for (int[] b : buildings) {
        List<Integer> p1 = new ArrayList<>();
        p1.add(b[0]);
        p1.add(-b[2]);
        points.add(p1);

        List<Integer> p2 = new ArrayList<>();
        p2.add(b[1]);
        p2.add(b[2]);
        points.add(p2);
    }
    //将所有坐标排序
    Collections.sort(points, new Comparator<List<Integer>>() {
        @Override
        public int compare(List<Integer> a, List<Integer> b) {
            int x1 = a.get(0);
            int y1 = a.get(1);
            int x2 = b.get(0);
            int y2 = b.get(1);
            
            if (x1!=x2) {
                return x1-x2;
            } else {
                return y1-y2;
            }
        }
    });
        TreeMap<Integer, Integer> tree = new TreeMap<>((a,b)->(b-a));
        tree.put(0,1);
        int preMax = 0;
        for (List<Integer> p:points) {
            int x = p.get(0);
            int y = p.get(1);
            if (y<0) {
                Integer v = tree.get(-y);
                if (v==null) {
                    tree.put(-y, 1);
                } else {
                    tree.put(-y, v+1);
                }
            } else {
                Integer v = tree.get(y);
                if (v==1) {
                    tree.remove(y);
                } else {
                    tree.put(y, v-1);
                }
            }
            int currMax = tree.firstKey();
            if (currMax!=preMax) {
                results.add(Arrays.asList(x,currMax));
                preMax = currMax;
            }
        }
        return results;
}


}