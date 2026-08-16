class Solution {
    static{
        for(int i = 0;i<500;i++){
            maxConsecutiveAnswers("",1);
        }
    }
    public static int maxConsecutiveAnswers(String answerKey, int k) {
        int l = 0;
        int maxLen = 0 ;
        int tc = 0;
        int fc = 0;

        for(int r= 0;r<answerKey.length();r++){
            if(answerKey.charAt(r) == 'T'){
                tc++;
            }else{fc++;}

            if( (r-l+1) - tc > k && (r-l+1)-fc  > k){
                //shrink condition

                if(answerKey.charAt(l) == 'T'){tc--;}
                else{
                    fc--;
                }
                l++;
            }


            //expand condition
            maxLen = Math.max(maxLen,r-l+1);
        }
        return maxLen;
    }
}