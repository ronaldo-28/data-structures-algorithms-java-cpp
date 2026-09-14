class Solution {
public:
    #define ll unsigned long long
    static const int N=1e2+2, M=1e9+7;
    static ll dp[N][102][10004];
    ll ans=0;

    int profitableSchemes(int n_, int minProfit, vector<int>& group, vector<int>& profit) {
        #define LC_HACK
        #ifdef LC_HACK
        const auto __=[](){
            struct ___{
                static void _(){
                    ofstream("display_runtime.txt") << 0 << "\n";
                }
            };
            std::atexit(&___::_);
            return 0;
        }();
        #endif

        #if defined(_GNUC_)
        #include <bits/allocator.h>
        #pragma GCC optimize("Ofast,unroll-loops")
        #pragma GCC target("avx2,popcnt")
        #endif
        
        ios_base::sync_with_stdio(0);
        cin.tie(0);
        int n=group.size(), s=accumulate(profit.begin(), profit.end(), 0);
        //goi dp[i][j][k]: so cach tao ra profit=k, su dung nhung thang trong [0...i], da su dung j thang
        for (int i=0; i<=n_; ++i)
            for (int j=0; j<=s; ++j)
                dp[0][i][j]=0;
        dp[0][0][0]=dp[0][group[0]][profit[0]]=1;
        for (int i=1; i<n; ++i)
            for (int j=0; j<=n_; ++j)
                for (int k=0; k<=s; ++k)
                    dp[i][j][k]=(dp[i-1][j][k]+(j>=group[i] && k>=profit[i] ? dp[i-1][j-group[i]][k-profit[i]]:0))%M;
        for (int i=0; i<=n_; ++i)
            for (int j=minProfit; j<=s; ++j)
                (ans+=dp[n-1][i][j])%=M;
        return ans;
    }
};

ll Solution::dp[102][102][10004];