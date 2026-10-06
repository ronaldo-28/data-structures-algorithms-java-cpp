class Solution {
    public String compressedString(String word) {
        char[] words = word.toCharArray();
        char[] res = new char[2 * word.length()];

        int count = 1, idx = 0;

        for(int i = 0 ; i < word.length() ; i++){
            if(i+1 < word.length() && words[i] == words[i+1] && count < 9){
                count++;
            }else{
                res[idx++] = (char)(count + '0');

                res[idx++] = words[i];

                count = 1;
            }
        }

        return new String(res,0,idx);
    }
}