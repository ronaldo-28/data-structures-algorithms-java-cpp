class Solution {
    public boolean isPrefixString(String s, String[] words) {
        int k=0;
        for(int i=0;i<words.length;i++){

            if(k==s.length()) return true;

            String word=words[i];
            if(!s.startsWith(word,k)){
                return false;
            }
            
            k+=word.length();
        }
        return k==s.length();
    }
}