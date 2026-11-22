class Solution {
public:
    int minOperations(vector<int>& nums) {
        int n = nums.size();
        if (n < 3) return -1;

        long long z = 1e18;
        vector<vector<long long>> f(n), g(n);

        auto h = [&](bool q) {
            vector<long long> w;
            for (int i=0; i<n; ++i) {
                int j=q?i:n-1-i;
                w.push_back((long long)nums[j]-i);
                w.push_back(1LL-i);
            }
            sort(w.begin(), w.end());
            w.erase(unique(w.begin(), w.end()), w.end());
            int m=w.size();

            vector<long long> p(m, z);
            for(int i=0; i<n; ++i) {
                int j=q?i:n-1-i;
                long long b=(long long)nums[j]-i;
                long long l=1LL-i;
                vector<long long> c(m, z);
                long long u=z;
                for(int k=0; k<m; ++k) {
                    u=(i>0)?min(u, p[k]):0;
                    if(w[k]>=l&&u<z) c[k]=u+abs(b-w[k]);
                }
                p=c;
                if(q) f[j]=p;
                else g[j]=p;
            }
            return w;
        };
        vector<long long> u=h(true), v=h(false);
        long long r=z;

        for(int p=1; p<n-1; ++p) {
            int i=0, j=0, x=u.size(), y=v.size();
            long long s=z, d=z;
            while(i<x || j<y) {
                long long a=i<x?u[i]+p:z;
                long long b=j<y?v[j]+n-p-1:z;
                if(a<=b) {
                    s=min(s, f[p-1][i]);
                    if(d<z && a<z) r=min(r, s+d+max(0LL, a-nums[p]));
                    ++i;
                } else {
                    d=min(d, g[p+1][j]);
                    if(s<z && b<z) r=min(r, s+d+max(0LL, b-nums[p]));
                    ++j;
                }
            }
        }
        return r>=z?-1:(int)r;
    }
};