class Solution {
public:
    long long minTotalTime(vector<int>& forward, vector<int>& backward, vector<int>& queries) {
        int n = forward.size();
        vector<long long> forwardPref(n + 1, 0); // forwardPref[i] = forward distance 0 -> i
        vector<long long> reversePref(n + 1, 0); // reversePref[i+1]-reversePref[i] = cost backward (i+1 -> i)
                                                  // wrap edge (0 -> n-1, cost backward[0]) stored at last slot

        for (int i = 0; i < n; i++) {
            forwardPref[i + 1] = forwardPref[i] + forward[i];
            reversePref[i + 1] = reversePref[i] + (i < n - 1 ? backward[i + 1] : backward[0]);
        }

        long long res = 0;
        int curr = 0;
        for (int v : queries) {
            int u = curr;

            long long fd = (u <= v)
                ? forwardPref[v] - forwardPref[u]
                : (forwardPref[n] - forwardPref[u]) + forwardPref[v];

            long long bd = (u >= v)
                ? reversePref[u] - reversePref[v]
                : reversePref[u] + (reversePref[n] - reversePref[v]);

            res += min(fd, bd);
            curr = v;
        }
        return res;
    }
};