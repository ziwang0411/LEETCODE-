class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        HashMap<Long, Integer> map1 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i + 1; j < nums1.length; j++) {
                long key = (long) nums1[i] * nums1[j];
                map1.put(key, map1.getOrDefault(key, 0) + 1);
            }
        }
        int result = 0;
        for (int num : nums2) {
            if (map1.containsKey((long) num * num)) {
                result += map1.get((long) num * num);
            }
        }
        HashMap<Long, Integer> map2 = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            for (int j = i + 1; j < nums2.length; j++) {
                long key = (long) nums2[i] * nums2[j];
                map2.put(key, map2.getOrDefault(key, 0) + 1);
            }
        }
        for (int num : nums1) {
            if (map2.containsKey((long) num * num)) {
                result += map2.get((long) num * num);
            }
        }
        return result;
    }
}