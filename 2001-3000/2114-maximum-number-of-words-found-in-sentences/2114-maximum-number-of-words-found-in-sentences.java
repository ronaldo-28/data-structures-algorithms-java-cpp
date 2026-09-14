class Solution {
    public int mostWordsFound(String[] sentences) {
        int maxWords = 0;
        for(int i = 0; i<sentences.length; i++){
            String str = sentences[i];
            int words = countWords(str);
            maxWords = Math.max(maxWords, words);
        }

        return maxWords;
    }

    public int countWords(String str){
        int spaces = 0;
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(ch == ' ') spaces++;
        }
        int words = spaces+1;
        return words;
    }
}