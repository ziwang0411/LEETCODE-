class Solution {
    static class TrieNode {
        TrieNode[] children;
        List<Integer> palindromeList;
        int index;

        TrieNode() {
            children = new TrieNode[26];
            palindromeList = new ArrayList<>();
            index = -1;
        }
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String str, int index) {
            TrieNode cur = root;
            int n = str.length();
            int i = 0;
            while (i < n) {
                if (isPalindrome(str, i, n - 1)) {
                    cur.palindromeList.add(index);

                }
                int ch = str.charAt(i) - 'a';
                if (cur.children[ch] == null) {
                    cur.children[ch] = new TrieNode();
                }
                cur = cur.children[ch];

                i++;
            }
            cur.index = index;
        }

        public void find(String str, int index, List<List<Integer>> result) {
            TrieNode cur = root;
            int n = str.length();
            int i = n - 1;
            for (; i >= 0; i--) {
                if (cur.index != -1 && isPalindrome(str, 0, i)) {
                    result.add(Arrays.asList(cur.index, index));
                }
                int ch = str.charAt(i) - 'a';
                if (cur.children[ch] == null) {
                    return;
                }
                cur = cur.children[ch];
            }
            if (cur.palindromeList != null) {
                for (Integer ind : cur.palindromeList) {
                    result.add(Arrays.asList(ind, index));
                }
            }
            if (cur.index != -1 && cur.index != index) {
                result.add(Arrays.asList(cur.index, index));
            }
        }

        public boolean isPalindrome(String str, int begin, int end) {
            while (begin < end && str.charAt(begin) == str.charAt(end)) {
                begin++;
                end--;
            }
            return begin >= end;
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        Trie trie = new Trie();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            trie.find(words[i], i, result);
        }
        return result;
    }
}