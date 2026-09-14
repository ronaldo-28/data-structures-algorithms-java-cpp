class Solution{
    vector<int> parent, size;
  public:
    int findUPar(int u){
        if(u == parent[u]) return u;
        return parent[u] = findUPar(parent[u]);
    }

    void unionbysize(int a, int b){
        int u = findUPar(a);
        int v = findUPar(b);

        if(u == v) return;

        if(size[u] < size[v]){
            parent[u] = v;
            size[v] += size[u];
        } 
        else{
            parent[v] = u;
            size[u] += size[v];
        }
    }

    vector<bool> areConnected(int n, int threshold, vector<vector<int>>& queries){
        parent.resize(n+1);
        size.resize(n+1, 1);

        for(int i=1; i<=n; i++){
            parent[i] = i;
        }

        for(int i=threshold+1; i<=n; i++){
            for(int j=2*i; j<=n; j+=i){
                unionbysize(i, j);
            }
        }

        vector<bool> ans;
        for(auto &q : queries){
            ans.push_back(findUPar(q[0]) == findUPar(q[1]));
        }

        return ans;
    }
};
