class Solution 
{
public:
    long long minimumWeight(int n, vector<vector<int>>& edges, int src1, int src2, int dest) 
    {
        vector<vector<pair<int,int>>>adj(n);
        int i;
        for(i=0;i<edges.size();i++)
        adj[edges[i][0]].push_back({edges[i][1],edges[i][2]});
        priority_queue<pair<long long int,int>,vector<pair<long long int,int>>,greater<pair<long long int,int>>>pq;
        vector<long long int>dp1(n,1e18);
        vector<long long int>dp2(n,1e18);
        dp1[src1]=0;
        pq.push({0,src1});
        while(!pq.empty())
        {
            long long int d=pq.top().first;
            int v=pq.top().second;
            pq.pop();
            if(d>dp1[v])
            continue;
            for(auto it:adj[v])
            {
                if(d+it.second<dp1[it.first])
                {
                    dp1[it.first]=d+it.second;
                    pq.push({dp1[it.first],it.first});
                }
            }
        }
        dp2[src2]=0;
        pq.push({0,src2});
        while(!pq.empty())
        {
            long long int d=pq.top().first;
            int v=pq.top().second;
            pq.pop();
            if(d>dp2[v])
            continue;
            for(auto it:adj[v])
            {
                if(d+it.second<dp2[it.first])
                {
                    dp2[it.first]=d+it.second;
                    pq.push({dp2[it.first],it.first});
                }
            }
        }
        vector<long long int>dp3(n,1e18);
        for(i=0;i<n;i++)
        {
            if(dp1[i]!=1e18 && dp2[i]!=1e18)
            {
                dp3[i]=dp1[i]+dp2[i];
                pq.push({dp3[i],i});
            }
        }
        while(!pq.empty())
        {
            long long int d=pq.top().first;
            int v=pq.top().second;
            pq.pop();
            if(v==dest)
            return d;
            if(d>dp3[v])
            continue;
            for(auto it:adj[v])
            {
                if(d+it.second<dp3[it.first])
                {
                    dp3[it.first]=d+it.second;
                    pq.push({dp3[it.first],it.first});
                }
            }
        }
        return -1;
    }
};