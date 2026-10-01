int fst[100000], prv[100000], vals[1000];

class Solution {
public:
    int longestSubarray(vector<int>& nums, int k) {
        memset(fst, -2, k * sizeof *fst);
        memset(prv, -1, k * sizeof *prv);

        int N = size(nums), bst = 0, bs = ((100000 + k-1)/k) * k;
        fst[0] = -1;
        for (int i = 0, cur = 0, nv = 0; i < N; ++i) {
            int ni = nums[i] + bs, pi = ni * 2 % k;
            cur = (cur + ni) % k;
            if (pi && prv[pi] < 0) vals[nv++] = pi;
            prv[pi] = i;

            if (fst[cur] >= -1) bst = max(bst, i - fst[cur]); else fst[cur] = i;
            for (int u = 0; u < nv; ++u) if (int v = vals[u]; prv[v] >= 0) {
                int p = (cur + k - v) % k;
                if (i - fst[p] > bst && fst[p] >= -1 && prv[v] > fst[p]) bst = i - fst[p];
            }
        }
        return bst;
    }
};