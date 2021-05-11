class Solution {
    public int hIndex(int[] citations) {
        int l = 0, r = citations.length-1;
        while (l<=r) {
            int mid = l+(r-l)/2;
            int h = citations.length-mid;
            if (citations[mid]>=h) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return citations.length-l;
    }
}public class 275 {
    
}
