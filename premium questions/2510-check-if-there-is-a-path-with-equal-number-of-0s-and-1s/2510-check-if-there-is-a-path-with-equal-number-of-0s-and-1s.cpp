class Solution {
public:
    bool isThereAPath(vector<vector<int>>& grid) {
        int n = grid.size();
        int m = grid[0].size();

        if((m + n - 1) % 2 == 1) {
            return false;
        }

        vector<vector<pair<int, int>>> v(n, vector<pair<int, int>>(m, {INT_MAX, INT_MIN}));
        if(grid[0][0] == 1) {
            v[0][0]= {1, 1};
        } else {
            v[0][0] = {0, 0};
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(i == j && i == 0) {
                    continue;
                }

                int minup = (i - 1) < 0 ? INT_MAX : v[i - 1][j].first;
                int maxup = (i - 1) < 0 ? INT_MIN : v[i - 1][j].second;
                int minleft = (j - 1) < 0 ? INT_MAX : v[i][j - 1].first;
                int maxleft = (j - 1) < 0 ? INT_MIN : v[i][j - 1].second;

                v[i][j].first = min(v[i][j].first, min(minup, minleft)) + grid[i][j];
                v[i][j].second = max(v[i][j].second, max(maxup, maxleft)) + grid[i][j];
            }
        }

        int mid = (m + n - 1) / 2;
        if(mid >= v[n - 1][m - 1].first && mid <= v[n - 1][m - 1].second) {
            return true;
        }

        return false;

        
    }
};