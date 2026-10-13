class Solution {
public:
    
    int minFlips(vector<vector<int>>& grid) {
        int m=grid.size();
        int n=grid[0].size();
        int ans=0;
        for(int i=0;i<m/2;i++)
        {
            for(int j=0;j<n/2;j++)
            {
                int cnt0=0;
                if(grid[i][j]==0)
                cnt0++;
                if(grid[m-1-i][j]==0)
                cnt0++;
                if(grid[m-1-i][n-1-j]==0)
                cnt0++;
                if(grid[i][n-1-j]==0)
                cnt0++;
                 ans += min(cnt0, 4 - cnt0);
            }
        }
        int diff=0;
        int cnt1=0;
        if(m%2==1)
        {
            for(int j=0;j<n/2;j++)
            {
                if(grid[m/2][j]!=grid[m/2][n-1-j])
                diff++;
                else if(grid[m/2][j]==1)
                cnt1+=2;
            }
        }
        if(n%2==1)
        {
            for(int i=0;i<m/2;i++)
            {
                if(grid[i][n/2]!=grid[m-i-1][n/2])
                diff++;
                else if(grid[i][n/2]==1)
                cnt1+=2;
            }
        }
        ans+=diff;
        if(cnt1%4!=0 && diff==0)
        ans+=2;
        if(m%2==1 && n%2==1)
        {
            if(grid[m/2][n/2]==1)
            ans++;
        }
        
        return ans;
    }
};