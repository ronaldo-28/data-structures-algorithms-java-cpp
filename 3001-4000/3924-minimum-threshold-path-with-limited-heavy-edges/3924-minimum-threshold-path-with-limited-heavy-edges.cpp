struct Edge{
    int v;
    int w;
};
struct Data{
    int v;
    int k;
};
const int N=1e3;
vector<Edge> adj[N];
int vis[N];
Data q[N*N];
class Solution {
public:
    int minimumThreshold(int n, vector<vector<int>>& edges, int source, int target, int k) {
        if(source == target){
            return 0;
        }
        int m=edges.size();
        int l=0;
        int r=0;
        for(int i=0;i<n;i++){
            adj[i].clear();
        }
        for(int i=0;i<m;i++){
            int u=edges[i][0];
            int v=edges[i][1];
            int w=edges[i][2];
            adj[u].push_back({v,w});
            adj[v].push_back({u,w});
            r=max(r,w);
        }
        int res=-1;
        while(l<=r){
            int mid=(l+r)/2;
            for(int i=0;i<n;i++){
                vis[i]=-1;
            }
            int a=0;
            int b=1;
            q[0]={source,k};
            vis[source]=k;
            bool ok=false;
            while(a<b){
                Data u=q[a++];
                for(auto&e:adj[u.v]){
                    int uk=u.k;
                    if(e.w>mid){
                        if(uk==0){
                            continue;
                        }
                        uk--;
                    }
                    if(e.v==target){
                        ok=true;
                        break;
                    }
                    if(vis[e.v]>=uk){
                        continue;
                    }
                    vis[e.v]=uk;
                    q[b++]={e.v,uk};
                }
                if(ok){
                    break;
                }
            }
            if(ok){
                r=mid-1;
                res=mid;
            }else{
                l=mid+1;
            }
        }
        return res;
    }
};