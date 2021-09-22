class Solution {
    int result;
    public int maxLength(List<String> arr) {
        List<Integer> bits = new ArrayList<>();
        result = 0;
        backtrack(arr, 0, 0, 0);
        return result;
    }
    
    private void backtrack(List<String> arr, int start, int currentBit, int length) {
        if (start==arr.size()) {
            result = Math.max(result, length);
            return;
        }
        String s = arr.get(start);
        int bit = 0;
        for (char c: s.toCharArray()) {
            int oldBit = bit;
            bit = bit | (1<<(c-'a'));
            if (oldBit==bit) {
                backtrack(arr, start+1, currentBit, length);
                return;
            }
        }
        
        if ((bit & currentBit)==0) {
            int newBit = currentBit| bit;
            int newLength = length+arr.get(start).length();
            backtrack(arr, start+1, newBit, newLength);
        }
        backtrack(arr, start+1, currentBit, length);
        
    }
}