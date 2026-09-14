class Solution {
    static{
        for(int i = 0; i < 100; i++){
            longestPrefix(" ");
        }
    }
    public static String longestPrefix(String s) {
        int n=s.length();
        char[] ch = s.toCharArray();
        int[] lps=new int[n];
        lps[0]=0;
        int pre=0;
        int suf=1;

        while(suf<n){
            if(ch[suf] == ch[pre]){
                
                lps[suf]=pre+1;
                pre++;
                suf++;
            }
            else{
                if(pre==0){
                    lps[suf]=0;
                    suf++;
                }
                else{
                    pre=lps[pre-1];
                }
            }
        }
        return s.substring(0,lps[n-1]);
    }
}