class Solution {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] strs = new String[nums.length];
        for (int i = 0; i<n; i++) {
            strs[i] = ""+nums[i];
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return (b+a).compareTo(a+b);
            }
        });
        if (strs[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for (String s: strs) {
            sb.append(s);
        }
        return sb.toString();
    }
}