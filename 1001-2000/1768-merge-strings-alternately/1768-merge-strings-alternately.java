class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        // int wordOneStart = 0;
        // int wordTwoStart = 0;
        // int n = word1.length();
        // int m = word2.length();
        // while( wordOneStart < n && wordTwoStart < m){
        //     sb.append(word1.charAt(wordOneStart));
        //     sb.append(word2.charAt(wordTwoStart));
        //     wordOneStart++;
        //     wordTwoStart++;
        // }

        // while(wordOneStart < n){
        //     sb.append(word1.charAt(wordOneStart));
        //     wordOneStart++;
        // }

        // while(wordTwoStart < m){
        //     sb.append(word2.charAt(wordTwoStart));
        //     wordTwoStart++;
        // }

        // return sb.toString();
          int n = word1.length();
          int m = word2.length();
          for(int i = 0; i < Math.min(n , m); i++){
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }

        if(n > m){
            sb.append(word1.substring(m));
        }else if(m > n){
            sb.append(word2.substring(n));
        }

        return sb.toString();
    }
}