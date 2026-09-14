class Solution {
    public int findTheLongestBalancedSubstring(String s) {
        int ans=0;
        int zeros=0;
        int ones=0;
        boolean b= s.charAt(0)=='1';
        for(int i=0;i<s.length();i++){
            if(b){
                if(s.charAt(i)=='1'){
                    ones++;
                }else{
                    ans=Math.max(ans,Math.min(ones,zeros));
                    zeros=1;
                    b = !b;
                }
            }else{
                if(s.charAt(i)=='0'){
                    zeros++;
                }else{
                    ones=1;
                    b = !b;
                }
            }
        }
        if(b){
            ans=Math.max(ans,Math.min(ones,zeros));
        }
        ans*=2;
        return ans;
    }
}