class Solution {
    public long maximumSubsequenceCount(String text, String pattern) {
        long f=0,s=0,count=0;
        char ch1=pattern.charAt(0);
        char ch2=pattern.charAt(1);
        for(char ch:text.toCharArray()){
            if(ch==ch2){
                count+=f;
                s++;
            }
            if(ch==ch1) f++;
        }
        long first=count+s;
        long second=count+f;
        return Math.max(first,second);
    }
}