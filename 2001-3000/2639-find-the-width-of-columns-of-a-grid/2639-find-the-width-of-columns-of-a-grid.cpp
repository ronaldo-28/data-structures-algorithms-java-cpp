class Solution {
public:
    vector<int> findColumnWidth(vector<vector<int>>& grid) {
        vector<int>ans;
        int m=grid.size();
        int n=grid[0].size();
        for(int j=0;j<n;j++){
            int maxi=0;
            for(int i=0;i<m;i++){
                int el=grid[i][j];
                int cnt=0;
                if(el==0) cnt=1;
                else{
                if(el<0){
                    cnt++;
                    el=el*-1;
                }
                while(el>0){
                    el=el/10;
                    cnt++;
                }
                }
                maxi=max(maxi,cnt);
            }
            ans.push_back(maxi);
        }
        return ans;
    }
};