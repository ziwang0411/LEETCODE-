class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int[] count = new int[5];
        int frogs = 0, res = 0;
        for (int i = 0; i<croakOfFrogs.length(); i++) {
            char c = croakOfFrogs.charAt(i);
            int index = "croak".indexOf(c);
            count[index]++;
            if (index==0) {
                res = Math.max(res, frogs+1);
                frogs++;
            } else if (--count[index-1]<0) return -1;
            else if (index==4) frogs--;
        }
        return frogs==0? res : -1;
    }
}