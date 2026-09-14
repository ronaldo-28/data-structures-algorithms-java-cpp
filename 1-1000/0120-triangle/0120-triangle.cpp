class Solution {
public:
    vector<vector<int>> dp;
    int n;
    int findMin(int i, int j, vector<vector<int>>& t) {
        if (dp[i][j] != INT_MAX) return dp[i][j];
        if (i == 0) return dp[0][0] = t[0][0];
        if (j == 0) return dp[i][j] = t[i][j] + findMin(i-1, j, t);
        if (i == j) return dp[i][j] = t[i][j] + findMin(i-1,j-1, t);
        return dp[i][j] = 
        t[i][j] + min(findMin(i - 1, j, t), findMin(i - 1, j - 1, t));
    }

    int minimumTotal(vector<vector<int>>& triangle) {
        n = triangle.size();
        dp.assign(n, vector<int>(n, INT_MAX));

        int ans = INT_MAX;  
        for (int j = 0; j < n; j++) {
            ans = min(ans, findMin(n - 1, j, triangle));
        }
        return ans;
    }      
};