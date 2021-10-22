class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        int end0=0, end1=0,has0=0;
        int mod = 1_000_000_007;
        for (char c: binary.toCharArray()) {
            if (c=='1') {
                end1=(end1+end0+1)%mod;
            } else {
                end0=(end1+end0)%mod;
                has0=1;
            }
        }
        return (end0+end1+has0)%mod;
    }
}