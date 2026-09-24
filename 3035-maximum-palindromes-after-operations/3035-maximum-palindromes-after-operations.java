class Solution {
    public int maxPalindromesAfterOperations(String[] words) {
        int n = words.length;
        int[] wordsFreq = new int[101];
        int[] charsFreq = new int[26];
        int odd = 0, even = 0, res = 0;
        for(int i = 0; i < n; i++){
            wordsFreq[words[i].length()]++;
            for(char c: words[i].toCharArray()) charsFreq[c - 'a']++;
        }
        
        for(int i = 0; i < 26; i++){
            odd += charsFreq[i] % 2;
            even += charsFreq[i] - charsFreq[i] % 2;
        }
        for(int i = 1; i <= 100; i++){
            while(wordsFreq[i]-- != 0){
                int evenNeed = i - i % 2;
                int oddNeed = i % 2;
                if(evenNeed > even || (oddNeed > odd && even == 0)) return res;
                even -= evenNeed;
                if(oddNeed != 0){
                    if(odd > 0) odd--;
                    else even--;
                }
                res++;
            }
            
        }
        return res;
    }
}