class Solution {
    public boolean isAdditiveNumber(String num) {
        return sol(num,0,-1,-1,0);
    }
    public boolean sol(String s,int idx,int p1,int p2,int c){
        if(idx==s.length()) return c>=3;
        int n=0;
        for(int i=idx;i<s.length();i++){
            n=n*10+s.charAt(i)-'0';
            if(p1==-1 || p2==-1 || p1+p2==n){
                if(sol(s,i+1,p2,n,c+1)) return true;
            }
            if(s.charAt(idx)-'0'==0) break;
        }
        return false;
    }
}