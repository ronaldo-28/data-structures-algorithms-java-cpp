#include <memory_resource>
pmr::unsynchronized_pool_resource pool;
constexpr int N=1e5+1;
long long nxt[N];
class Solution {
public:
    static void print(auto& c){
        for (auto x: c)cout<<x<<", ";
        cout<<endl;
    }
    static vector<long long> distance(vector<int>& nums) {
        pool.release();
        pmr::unordered_map<int, long long> idx(&pool);
        const int n=nums.size();
        for(int i=0; i<n; i++){
            const int x=nums[i];
            auto it=idx.find(x);
            if (it==idx.end()) idx[x]=i, nxt[i]=-1;
            else nxt[i]=idx[x], idx[x]=i;
        }
        vector<long long> ans(n, 0);
        // trasverse idx
        for(auto& [x, h]: idx){
        //    cout<<"x="<<x<<":"; print(v);
            if (nxt[h]==-1) continue;
            long long total=0, prefix=0;
            int vz=0;
            for(int j=h; j!=-1; j=nxt[j]){
                total+=j;
                vz++;
            }
            for(int i=vz-1, j=h; j!=-1; i--,j=nxt[j]){
                ans[j]=(2LL*i-vz+2)*j+2LL*prefix-total;
                prefix+=j;
            }
        }
        return ans;
    }
};

auto init = []() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    return 'c';
}();