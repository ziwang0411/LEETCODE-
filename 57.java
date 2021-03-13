class Solution {
    public int[][] insert(int[][] val, int[] ni) {
        int n = val.length;
        if(n == 0){
            int x[][] = new int[1][2];
            x[0][0] = ni[0];
            x[0][1]  = ni[1];
            return x;
        }
        List<int[]> fin = new ArrayList();
        for (int[] x: val) {
            if (x[1]<ni[0]) fin.add(x);
            
            else if (x[0]>ni[1]) {
                fin.add(ni);
                ni = x;
            }
            else {
                ni[0] = Math.min(x[0],ni[0]);
                ni[1] = Math.max(x[1],ni[1]);
            }
        }
        fin.add(ni);
        return fin.toArray(new int[fin.size()][]);
    }
}