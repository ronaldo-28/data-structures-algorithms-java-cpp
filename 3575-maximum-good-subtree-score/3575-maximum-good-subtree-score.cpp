typedef long long ll;
const ll mod = 1e9 + 7;
const int N = 1 << 10;
class Solution {
public:
    int dp[N];          
    int best[N];
    int *a;
    vector<vector<int>> g;
    int Ans = 0;
    // mask = digits still FREE; returns best sum using only those digits
    // 3^Number Of bits = 3^10
    int solve(int mask){
        if(mask == 0) return 0;
        int &ans = dp[mask];
        if(~ans) return ans;
        // lowest bit
        int low = mask & -mask;              
        int rest = mask ^ low;
        // without it
        ans = solve(rest);            
        // try each free bit with it   
        for(int s = rest; ; s = (s - 1) & rest){
            int m = s | low;             
            if(best[m]) ans = max(ans, best[m] + solve(mask ^ m));
            if(s == 0) break;
        }
        return ans;
    }

    vector<int> order;
    void dfs(int u){
        int st = order.size();
        order.push_back(u);
        for(int v : g[u]) dfs(v);
        memset(best, 0, sizeof(best));
        for(int i = st ; i<order.size();i++){
            int node = order[i];
            int val = a[node], m = 0;
            while(val){
                int bit = 1 << (val % 10);
                if(m & bit){ m = 0; break; }  
                m |= bit;
                val /= 10;
            }
            if(m) best[m] = max(best[m], a[node]);
        }

        memset(dp, -1, sizeof(dp));    
        Ans = (Ans + solve(N - 1)) % mod;    
    }
    
    int goodSubtreeSum(vector<int>& vals, vector<int>& par) {
        int n = vals.size();
        a = vals.data();
        g.assign(n, {});
        for(int i = 1; i < n; i++) g[par[i]].push_back(i);
        dfs(0);
        return Ans;
    }
};