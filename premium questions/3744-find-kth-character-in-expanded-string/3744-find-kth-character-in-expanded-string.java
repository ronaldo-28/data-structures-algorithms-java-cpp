class Solution {
    public char kthCharacter(String s, long k) {
        int idx = 0;
        char ans = ' ';
        int n = s.length();
        for(int i=0; i < n; i++){
            char ch = s.charAt(i);
            //if space then only deduce 1 char 
            if(ch == ' '){
                if(k == 0){
                    return ' ';
                }else{
                    k -= 1;
                    idx = 0;
                }
            }else{
                long repeat = idx + 1l;
                if(k < repeat){
                    return ch;
                }
                idx++;
                k -= repeat;
            }
        }
        return ans;
    }
}