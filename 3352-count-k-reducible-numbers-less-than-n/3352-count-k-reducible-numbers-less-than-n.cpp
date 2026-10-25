int cntR[801] ; 
vector<vector<int>> dp ; 
const int mod = 1e9 + 7 ; 


int find(long long base, int pow){
    long long ans = 1 ; 

    while( pow ){
        if( pow & 1 ){
            ans = (ans * base)%mod ; 
        }

        base = (base*base) % mod ; 
        pow >>= 1 ; 
    }

    return ans ; 
}


void solve(){
    int n = 801 ; 
    dp.resize(n, vector<int>(n)) ; 
    cntR[0] = 6 ; 
    long long fact = 1 ; 
    
    for(int i=800 ; i>=1 ; i--){
        fact = (fact*i)%mod ; 
        int k = i ; 
        int cnt = 0 ; 
        while( k>1 ){
           k = __builtin_popcount(k) ; 
           cnt++ ; 
        }

        cntR[i] = cnt ; 
    }

    vector<int> invFact(n) ; 
    invFact[800] = find(fact, mod-2) ; 
    fact = 1 ; 
    for(long long i=800 ; i>=1 ; i--)
        invFact[i-1] = (i*invFact[i]) % mod ;
    
    dp[0][0] = 1 ; 
    for(int i=1 ; i<n ; i++){
        fact = (fact*i)%mod ; 
        for(int j=0 ; j<=i ; j++)
            dp[i][j] = ((fact*invFact[j])%mod)*invFact[i-j]%mod ; 
    }

}


class Solution {
public:
    int countKReducibleNumbers(string s, int k) {
        if( dp.empty() )
            solve() ; 
        int n = s.size() ; 
        int cnt = 0 ; 
        long long ans = 0 ; 

        cout<<dp[3][1]<<endl ; 
        for(int i=0 ; i<n ; i++){
            if( s[i]=='1' ){
                int m = n-i-1 ; 
                for(int j=0 ; j<=m ; j++){
                    if( cntR[cnt+j]<k ){
                        ans = (ans + dp[m][j])%mod ; 
                    } 
                        
                }
                cnt++ ; 
            }
        }

        return ans ; 
    }
};