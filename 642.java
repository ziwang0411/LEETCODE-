class AutocompleteSystem {
    private Trie root;
    private String currSentence = "";
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        for (int i = 0; i<sentences.length; i++) {
            insert(root, sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        List<Node> list = new ArrayList<>();
        if (c=='#') {
            insert(root, currSentence, 1);
            currSentence="";
        } else {
            currSentence+=c;
            list = lookup(root, currSentence);
        }
        Collections.sort(list, (a,b)->a.times==b.times?a.sentence.compareTo(b.sentence):b.times-a.times);
        for (int i = 0; i<Math.min(3, list.size()); i++) {
            res.add(list.get(i).sentence);
        }
        return res;
    }
    
    private void insert(Trie root, String s, int times) {
        Trie curr= root;
        for (int i = 0; i<s.length(); i++) {
            int index = s.charAt(i)==' '? 26: (s.charAt(i)-'a');
            if (curr.children[index]==null) {
                curr.children[index]=new Trie();
            }
            curr = curr.children[index];
        }
        curr.times +=times;
    }
    
    private List<Node> lookup(Trie root, String s) {
        Trie curr = root;
        List<Node> result = new ArrayList<>();
        for (int i = 0; i<s.length(); i++) {
            int index = s.charAt(i)==' '? 26: (s.charAt(i)-'a');
            if (curr.children[index]==null) {
                return result;
            }
            curr = curr.children[index];
        }
        findStrings(curr, s, result);
        return result;
    }
    
    private void findStrings(Trie curr, String s, List<Node> result) {
        if (curr==null) return;
        if (curr.times>0) result.add(new Node(curr.times, s));
        for (char c = 'a'; c<='z'; c++) {
            findStrings(curr.children[c-'a'], s+c, result);
        }
        findStrings(curr.children[26], s+" ", result);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
class Node {
    int times;
    String sentence;
    public Node(int times, String s) {
        this.times = times;
        this.sentence=s;
    }
}
class Trie {
    int times;
    Trie[] children = new Trie[27];
}