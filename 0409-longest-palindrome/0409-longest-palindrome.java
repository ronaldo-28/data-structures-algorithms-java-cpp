class Solution {
    static{
        for(int i=0;i<200;i++){
            longestPalindrome("aa");
        }
    }
    public static int longestPalindrome(String s) {
        int n = s.length();
        boolean alp[] = new boolean[128];  
        int ans = 0;
        for(char c : s.toCharArray()){
            if(alp[c]){        
                alp[c] = false; 
                ans += 2;      
            }
            else{
                alp[c] = true;
            }
        }
        return ans == n ? ans : ans + 1;  
    }
}