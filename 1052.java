class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = customers.length;
        int[] satisfied = new int[n];
        int sum = 0;
        for (int i = 0; i<n; i++) {
            if (grumpy[i]==1 && i>=X) {
                satisfied[i]=0;
            }
            else satisfied[i] = customers[i];
            sum+=satisfied[i];
        }
        //1,1,1,0,1,0,7,0
        
        int maxValue = sum;
        for (int i = 0; i+X<n; i++) {
            if (grumpy[i]==1) sum-=customers[i];
            if (grumpy[i+X]==1) sum+=customers[i+X];
            maxValue = Math.max(sum, maxValue);
        }
        return maxValue;
    }
}