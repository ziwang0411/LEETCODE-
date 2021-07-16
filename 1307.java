class Solution {
    public boolean isSolvable(String[] words, String result) {
        Set<Character> s = new HashSet<>();
        int[] cnt = new int[26];
        boolean[] nonZero = new boolean[26];
        for(String w:words){
            char[] cs = w.toCharArray();
            for(int i=0;i<cs.length;i++){
                if(i==0 && cs.length>1){
                    nonZero[cs[i]-'A'] = true;
                }
                s.add(cs[i]);
                cnt[cs[i]-'A'] += Math.pow(10,cs.length-i-1);
            }
        }
        
        char[] cs = result.toCharArray();
        for(int i=0;i<cs.length;i++){
            if(i==0&& cs.length>1){
                nonZero[cs[i]-'A'] = true;
            }
            s.add(cs[i]);
            cnt[cs[i]-'A'] -= Math.pow(10,cs.length-i-1);
        }
        
        return dfs(cnt,s,0,0,nonZero,new boolean[10]);
        
    }
    
    public boolean dfs(int[] cnt,Set<Character> s,int idx,int cur,boolean[] non,boolean[] used){
        
        while(idx < 26 && cnt[idx]==0){
            idx++;
        }
        if(idx==26){
            return cur==0;
        }
        for(int i=0;i<10;i++){
            if(used[i] || ( i==0 && non[idx])){
                continue;
            }
            used[i] = true;

            if(dfs(cnt,s,idx+1,cur+i * cnt[idx],non,used)){
                return true;
            }
            used[i] = false;
        }
        return false;
        
    }
}