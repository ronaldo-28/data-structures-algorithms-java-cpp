class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        //Assume sentence2 to be longer
        if (sentence1.length() > sentence2.length()) return areSentencesSimilar(sentence2,sentence1);
        int n = sentence1.length(), m = sentence2.length();
        char[] s = sentence1.toCharArray();
        char[] t = sentence2.toCharArray();
        int i=0, j=0; 
        while (i < n) {
            if (s[i] == t[j]) {
                i++; j++;
            } else { 
                i--; j--; 
                while (i >= 0 && s[i] != ' ') {
                    i--; 
                    j--;
                }
                break;
            }
        }
        if (i == n) {   
            if (j == m || t[j] == ' ') return true;
            else {
            //unroll the loop, allow suffix to match if possible
            i--;
            while (i >=0 && s[i] != ' ') i--;
        }
        }
        int p=n-1, q=m-1;
        while (i < p) {
            if (s[p] == t[q]) {
                p--; q--;
            } else break;
        }
        return i==p && t[q] == ' ';
    }
}