class Solution {
public:
    int countWinningSequences(string s) {
        const long long MOD = 1000000007;
        int n = s.size();
        int OFFSET = n;
        int SIZE = 2 * n + 1;
        
        auto idx = [](char c) -> int {
            if (c == 'F') return 0;
            if (c == 'W') return 1;
            return 2;
        };
        
        vector<int> a(n);
        for (int i = 0; i < n; i++) a[i] = idx(s[i]);
        
        // b beats ai iff b == (ai+1)%3; ai beats b iff ai == (b+1)%3
        auto val = [](int b, int ai) -> int {
            if (b == ai) return 0;
            if (b == (ai + 1) % 3) return 1;   // Bob wins the round
            return -1;                          // Alice wins the round
        };
        
        static long long dp[3][2001];
        static long long newdp[3][2001];
        static long long total[2001];
        
        for (int b = 0; b < 3; b++)
            for (int d = 0; d < SIZE; d++) dp[b][d] = 0;
        
        for (int b = 0; b < 3; b++) {
            int c = val(b, a[0]);
            dp[b][OFFSET + c] = 1;
        }
        
        for (int i = 1; i < n; i++) {
            for (int d = 0; d < SIZE; d++) {
                total[d] = dp[0][d] + dp[1][d] + dp[2][d];
                if (total[d] >= MOD) total[d] %= MOD;
            }
            
            for (int b = 0; b < 3; b++)
                for (int d = 0; d < SIZE; d++) newdp[b][d] = 0;
            
            for (int b = 0; b < 3; b++) {
                int c = val(b, a[i]);
                for (int d = 0; d < SIZE; d++) {
                    int nd = d + c;
                    if (nd < 0 || nd >= SIZE) continue;
                    long long v = total[d] - dp[b][d];
                    if (v < 0) v += MOD;
                    newdp[b][nd] = (newdp[b][nd] + v) % MOD;
                }
            }
            
            for (int b = 0; b < 3; b++)
                for (int d = 0; d < SIZE; d++) dp[b][d] = newdp[b][d];
        }
        
        long long ans = 0;
        for (int b = 0; b < 3; b++)
            for (int d = OFFSET + 1; d < SIZE; d++)
                ans = (ans + dp[b][d]) % MOD;
        
        return (int)ans;
    }
};