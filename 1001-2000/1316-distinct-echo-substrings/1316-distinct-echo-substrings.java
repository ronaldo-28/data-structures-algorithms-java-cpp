class Solution {
    HashSet<String> set = new HashSet<>();
    public int distinctEchoSubstrings(String text) {
        
        char[] cs = text.toCharArray();
        int n = cs.length;
        int len = n - 1;
        for(int i = 0; i <= len; i++)   
              len = Math.min(len, kmp(text, i));
        return set.size();
    }

    int kmp(String t,int l){
        int n = t.length();
        int[] f = new int[n];
        char[] s = t.substring(l).toCharArray();
        
        int pos = 0;
        for(int i = 1; i + l < n; i++){

            while(pos > 0 && s[i] != s[pos]) 
                 pos = f[pos-1];
            if(s[i]==s[pos])
                pos++;

            f[i] = pos;
            if(pos > 0 &&( (i+1) % (i+1-pos) == 0) ){
                if(((i + 1)/ (i + 1 - pos)) % 2 == 0)  
                    set.add(t.substring(l, l + (i + 1)/2));
            }
        }
        return (f[n - 1] > 0  && (n % (n - f[n-1]) == 0))? (n - f[n-1]):Integer.MAX_VALUE;  
    }
}