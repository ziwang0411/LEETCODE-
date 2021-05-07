class Solution {
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        boolean[] odd = new boolean[n];
        boolean[] even = new boolean[n];
        odd[n-1] = true;
        even[n-1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(arr[n-1], n-1);
        int res = 1;
        for (int i = n-2; i>=0; i--) {
            int curr = arr[i];
            Integer oddKey = map.ceilingKey(curr);
            if (oddKey!=null) {
                odd[i] = even[map.get(oddKey)];
            }
            Integer evenKey = map.floorKey(curr);
            if (evenKey!=null) {
                even[i] = odd[map.get(evenKey)];
            }
            if (odd[i]) res++;
            map.put(arr[i], i);
        }
        return res;
    }
}