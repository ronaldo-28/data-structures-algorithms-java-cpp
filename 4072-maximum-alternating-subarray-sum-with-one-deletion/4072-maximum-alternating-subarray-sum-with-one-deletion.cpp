class Solution {
public:
    long long maxAlternatingSum(vector<int>& a) {
        long long p = -4e18, m = p, q = p, r = p, z = p, x, P, M, Q, R;

        for (int v : a)
            x = v,
            P = max(x, m + x),
            M = p - x,
            Q = max(r + x, p),
            R = max(q - x, m),
            p = P,
            m = M,
            q = Q,
            r = R,
            z = max(max(z, p), max(m, max(q, r)));

        return z;
    }
};