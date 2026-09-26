#include<bits/stdc++.h>
using namespace std;
class Solution{
    int L,W,lim,top;
    void gen(int x,int S,vector<pair<int,int>>&r,vector<int>&ds,vector<int>&q){
        r.clear();q.clear();
        fill(ds.begin(),ds.end(),-1);
        ds[x]=0;q.push_back(x);
        for(size_t h=0;h<q.size();++h){
            int u=q[h];
            if(u>0&&u<=S)r.push_back({u,ds[u]});
            if(u*2<=S&&ds[u*2]<0){ds[u*2]=ds[u]+1;q.push_back(u*2);}
            if(u>0&&ds[u>>1]<0){ds[u>>1]=ds[u]+1;q.push_back(u>>1);}
        }
    }
    void step(vector<uint64_t>&nd,const vector<uint64_t>&d,const vector<pair<int,int>>&r){
        for(size_t k=0;k<r.size();++k){
            int v=r[k].first,c=r[k].second,w=v>>6,b=v&63;
            if(c>=lim)continue;
            for(int i=min(top,lim-1-c);i>=0;--i){
                const uint64_t*s=&d[(size_t)i*W];
                uint64_t*t=&nd[(size_t)(i+c)*W];
                for(int j=W-1-w;j>=0;--j){
                    uint64_t z=s[j];
                    if(!z)continue;
                    t[j+w]|=z<<b;
                    if(b&&j+w+1<W)t[j+w+1]|=z>>(64-b);
                }
            }
        }
    }
public:
    int minOperations(vector<int>&nums,int S){
        int n=(int)nums.size(),mx=S;
        for(int i=0;i<n;++i)mx=max(mx,nums[i]);
        int bd=0,bm=0;
        while(((long long)1<<(bd+1))<=mx)++bd;
        while(((long long)1<<(bm+1))<=S)++bm;
        L=1+(bd+bm)*min(n,bm+1);W=(S>>6)+1;lim=L;top=0;
        vector<uint64_t>d((size_t)L*W,0),nd;
        d[0]=1;
        vector<int>ds(mx+1),q;vector<pair<int,int>>r;
        int wS=S>>6,bS=S&63;
        for(int e=0;e<n;++e){
            gen(nums[e],S,r,ds,q);
            int mc=0;
            for(size_t i=0;i<r.size();++i)mc=max(mc,r[i].second);
            nd=d;
            step(nd,d,r);
            d.swap(nd);
            top=min(L-1,top+mc);
            for(int c=0;c<lim;++c)if(d[(size_t)c*W+wS]>>bS&1){lim=c;break;}
            if(!lim)return 0;
        }
        return lim<L?lim:-1;
    }
};