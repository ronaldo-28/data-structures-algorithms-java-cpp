class Solution {
public:
    
    bool* rest;
    int* parentList;
    int* size;
    int parent0sz;
    
    int parent(int v)
    {
        if (parentList[v] == v)
        {
            return v;
        }
        
        return parent(parentList[v]);
    }
    
    int combine(int v1, int v2)
    {
        int pv1 = parent(v1);
        int pv2 = parent(v2);
        if (pv1 == pv2)
        {
            return 0;
        }
        if (pv2 == 0 || pv1 != 0)
        {
            parentList[pv1] = pv2;
            size[pv2] += size[pv1];    
        }else if (pv1 == 0)
        {
            parentList[pv2] = pv1;
            size[pv1] += size[pv2];
        }
        return 1;
    }
    
    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        parentList = new int[n]();
        size = new int[n]();
        rest = new bool[n]();
        for (int i = 0; i< n; i++)
        {
            parentList[i] = i;
            size[i] = 1;
        }
        
        for (int i = 0; i<restricted.size(); i++)
        {
            rest[restricted[i]] = true;
        }
        
        for (int i = 0; i < edges.size(); i++)
        {
            if (rest[edges[i][0]] || rest[edges[i][1]])
            {
                continue;
            }
            
            combine(edges[i][0], edges[i][1]);
        }
        return size[0];
    }
};