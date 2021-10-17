class Solution {
    public int findMaximumXOR(int[] nums) {
        //build a bitTrie
        TrieNode root = new TrieNode();
        int maxNum = nums[0];
        for (int num:nums) maxNum = Math.max(maxNum, num);
        String maxString = Integer.toBinaryString(maxNum);
        int length = maxString.length();
        int maxXor = 0;
        for (int num:nums) {
            char[] biString = create(num, length);
            int currXor = 0;
            TrieNode head = root;
            TrieNode XorNode = root;
            for (char c:biString) {
                TrieNode child = head.children.getOrDefault(c, new TrieNode());
                head.children.put(c, child);
                head = child;
                
                currXor<<=1;
                char optimal = c=='1'?'0':'1';
                if (XorNode.children.containsKey(optimal)) {
                    currXor+=1;
                    XorNode = XorNode.children.get(optimal);
                }
                else {
                    XorNode = XorNode.children.get(c);
                }
            }
            maxXor = Math.max(maxXor, currXor);
            
        }
        
        return maxXor;
    }
    
    class TrieNode {
        HashMap<Character, TrieNode> children;
        TrieNode() {
            children = new HashMap<>();
        }
    }
    
    private char[] create(int num, int length) {
        char[] result = new char[length];
        Arrays.fill(result,'0');
        char[] numArray = Integer.toBinaryString(num).toCharArray();
        int shift=result.length-numArray.length;
        for (int i = 0; i<numArray.length;i++) {
            result[i+shift]=numArray[i];
        }
        return result;
    }
}