class Solution {
    public int latestDayToCross(int row, int col, int[][] cells) {
        int[][] board = new int[row+2][col];
        for (int[] cell: cells) {
            board[cell[0]][cell[1]-1]=1;
        }
        UF uf = new UF((row+2)*col);
        for (int i = 0; i<row+1; i++) {
            for (int j = 0; j<col; j++) {
                if (i==0) uf.union(0,i*col+j);
                else {
                    if (board[i][j]==0) {
                        if (i>0 && board[i-1][j]==0) uf.union((i-1)*col+j, i*col+j);
                        if (j>0 && board[i][j-1]==0) uf.union(i*col+j-1, i*col+j);
                    }
                } 
            }
        }
        for (int j = 0; j<col; j++) {
            if (j>0) uf.union((row+1)*col, (row+1)*col+j);
            if (board[row][j]==0) uf.union((row+1)*col, (row)*col+j);
        }
        int res = cells.length-1;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while (uf.find(0)!=uf.find((row+1)*col)) {
            int[] cell = cells[res];
            int i = cell[0], j = cell[1]-1;
            board[i][j] = 0;
            for (int[] dir : dirs) {
                int nr = i+dir[0], nc = j+dir[1];
                if (nr>=0 && nr<row+2 && nc>=0 && nc<col && board[nr][nc]==0) {
                    uf.union(nr*col+nc, i*col+j);
                }
            }
            res--;
        }
        return res+1;
    }
}
class UF {
    int[] parents;
    public UF(int n ) {
        parents = new int[n];
        for (int i = 0; i<n; i++) {
            parents[i]=i;
        }
    }
    public int find(int x) {
        if (parents[x]!=x) parents[x]=find(parents[x]);
        return parents[x];
    }
    public void union(int x, int y) {
        int px = find(x), py = find(y);
        parents[py]= px;
    }
    
}