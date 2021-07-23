class Solution {
    int[] res;
    boolean found = false;

    public int[] constructDistancedSequence(int n) {
        res = new int[n * 2 - 1];
        boolean[] used = new boolean[n + 1];
        backtrack(new int[n * 2 - 1], 0, used);
        return res;
    }

    private void backtrack(int[] sequence, int index, boolean[] used) {
        if (found)
            return;
        if (index == sequence.length) {
            if (isGreater(sequence, res)) {
                for (int i = 0; i < res.length; i++) {
                    res[i] = sequence[i];
                    found = true;
                }
            }
            return;
        }
        if (sequence[index] != 0) {
            backtrack(sequence, index + 1, used);
            return;
        }
        for (int i = used.length - 1; i >= 1; i--) {
            if (!used[i]) {
                if (i == 1) {
                    sequence[index] = i;
                    used[i] = true;
                    backtrack(sequence, index + 1, used);
                    sequence[index] = 0;
                    used[i] = false;

                } else {
                    if (index + i < sequence.length && sequence[index + i] == 0) {
                        sequence[index + i] = i;
                        used[i] = true;
                        sequence[index] = i;
                        backtrack(sequence, index + 1, used);
                        sequence[index] = 0;
                        sequence[index + i] = 0;
                        used[i] = false;

                    }
                }
            }
        }
    }

    private boolean isGreater(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i])
                continue;
            if (a[i] < b[i])
                return false;
            if (a[i] > b[i])
                return true;
        }
        return true;
    }

}