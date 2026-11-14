class Solution {
    public String resultingString(String s) {
       char[]st=new char[s.length()];
       int top=-1;
       for(char a:s.toCharArray()){
        if(top>=0 && (Math.abs(a-st[top])==1 || Math.abs(a-st[top])==25)){
            top--;
        }
        else{
            st[++top]=a;
        }
       }
       return new  String(st,0,top+1);
    }
}