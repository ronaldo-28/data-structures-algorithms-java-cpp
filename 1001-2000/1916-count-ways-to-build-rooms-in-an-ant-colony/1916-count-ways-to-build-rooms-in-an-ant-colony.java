class Solution {
    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;
        int[] inDegree = new int[n];
        
        // 1. Calculate in-degrees (children count)
        // We start at 1 because prevRoom[0] is -1
        for (int i = 1; i < n; i++) {
            inDegree[prevRoom[i]]++;
        }

        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            size[i] = 1;
        }

        // 2. High-performance primitive array Queue
        int[] q = new int[n];
        int head = 0, tail = 0;

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q[tail++] = i;
            }
        }

        // 3. Bottom-Up BFS to calculate subtree sizes
        while (head < tail) {
            int u = q[head++];
            if (u == 0) continue; 
            
            int p = prevRoom[u];
            size[p] += size[u];
            if (--inDegree[p] == 0) {
                q[tail++] = p;
            }
        }

        // 4. The Mathematical Masterstroke
        long fact = 1;
        long denom = 1;
        int MOD = 1000000007;

        for (int i = 1; i <= n; i++) {
            fact = (fact * i) % MOD;
            denom = (denom * size[i - 1]) % MOD;
        }

        return (int) ((fact * power(denom, MOD - 2, MOD)) % MOD);
    }

    // Fast Modular Exponentiation
    private long power(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}