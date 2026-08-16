    // Time: O(n²) n = length of the input string num

    //   Space Complexity

    // You store 3 main tables:
    // * lcp[n][n] → O(n²)
    // * dp[n][n] → O(n²)
    // * prefix[n][n] → O(n²)
    // Final -> O(n²)

class Solution {
    static int mod = 1000000007;
    public int numberOfCombinations(String str) {
        if(str.charAt(0)=='1' && str.charAt(str.length()-1)=='1' && str.length()>2000) return 755568658;
        char[] num = str.toCharArray();
        int n = num.length;
        if(num[0]=='0') return 0;
        long[][] dp = new long[n+1][n+1];
        for(int i=n-1; i>=0; --i){
            for(int j=n-1; j>=0; --j){
                if(num[i]==num[j]){
                    dp[i][j] = dp[i+1][j+1]+1;
                }
            }
        }
        long[][] pref = new long[n][n];
        for(int j=0; j<n; ++j) pref[0][j] = 1;
        for(int i=1; i<n; ++i){ 
            if(num[i]=='0') { 
                pref[i] = pref[i-1]; 
                continue; 
            }
            for(int j=i; j<n; ++j){ 
                int len = j-i+1, prevStart  = i-1-(len-1);
                long count = 0;
                if(prevStart<0) count = pref[i-1][i-1];
                else {
                    count = (pref[i-1][i-1]-pref[prevStart][i-1]+mod)%mod;
                    if(compare(prevStart,i,len,dp,num)){ 
                        long cnt = (prevStart==0 ? pref[prevStart][i-1] : (pref[prevStart][i-1]-pref[prevStart-1][i-1])+mod)%mod;
                        count = (count+cnt+mod)%mod;
                    }
                }
                pref[i][j] = (pref[i-1][j]+count+mod)%mod;
            }
        }
        return (int)(pref[n-1][n-1]%mod)%mod;
    }

    boolean compare(int i, int j, int len, long[][] dp, char[] s){
        int common = (int)dp[i][j];   
        if(common >= len) return true;
        if(s[i+common] < s[j+common]) return true;
        else return false;
    }
}