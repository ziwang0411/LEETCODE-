class Solution {
    public int minFlips(int[][] mat) {
        State initState = new State(mat, 0);
        if (initState.isDone())
            return initState.step;
        Set<String> visited = new HashSet<>();
        visited.add(initState.toString());
        Queue<State> queue = new LinkedList<>();
        queue.add(initState);
        while (!queue.isEmpty()) {
            // System.out.println(queue.size());
            State state = queue.poll();
            if (state.isDone())
                return state.step;
            for (State next : state.getNeighbors()) {
                String key = next.toString();
                if (!visited.contains(key)) {
                    queue.add(next);
                    visited.add(key);
                }
            }
        }
        return -1;
    }

}

class State {
    int[][] mat;
    int m;
    int n;
    int step;

    public State(int[][] mat, int step) {
        this.mat = mat;
        m = mat.length;
        n = mat[0].length;
        this.step = step;
    }

    public boolean isDone() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1)
                    return false;
            }
        }
        return true;
    }

    public List<State> getNeighbors() {
        List<State> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res.add(flip(i, j));
            }
        }
        return res;
    }

    public State flip(int row, int col) {
        int[][] next = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == row && j == col) || (i == row && j == col + 1) || (i == row + 1 && j == col)
                        || (i == row && j == col - 1) || (i == row - 1 && j == col))
                    next[i][j] = 1 - mat[i][j];
                else
                    next[i][j] = mat[i][j];
            }
        }
        return new State(next, this.step + 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(mat[i][j]);
            }
        }
        return sb.toString();
    }
}