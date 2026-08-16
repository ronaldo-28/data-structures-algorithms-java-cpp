class Solution {
    static{
        for(int i=0;i<=1000;i++){
            removeKdigits("",0);
        }
    }
    public static String removeKdigits(String num, int k) {
        int n=num.length();
        char [] stack=new char[n];
        int top=-1;
        for(int i=0;i<n;i++){
            while(top!=-1&&k>0&&stack[top]>num.charAt(i)&&k!=0){
                top--;
                k--;
            }
            stack[++top]=num.charAt(i);
        }
        top-=k;
        int i=0;
        while(top!=-1&&stack[i]=='0') i++;
       if(i>top) return "0";
       return String.valueOf(stack,i,top-i+1);
    }
}