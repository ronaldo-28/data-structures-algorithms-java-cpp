class Solution {
public:
    int minimumOperations(vector<vector<int>>& grid) {
        // here first int is col index , second int is number and third index is freq(number)
        map<pair<int , int> , int> cnt;
        // Count occurrences of values in each column
        for(int i = 0 ; i < grid[0].size() ; i++ ){
            for(int j = 0 ; j < grid.size() ; j++ ){
                cnt[{i , grid[j][i]}]++;
            }
        }

        // Initialize dp table with -1
        vector<vector<int>> dp(grid[0].size()+1 , vector<int>(11 , -1));

        // here sending 10 for prev value it's not present in grid so it will always be non existing
        return helper(grid , cnt , 0 , 10 , dp);
    }

    int helper(vector<vector<int>>& grid , map<pair<int , int> , int>& cnt , int idx , int prev , vector<vector<int>>& dp){
        // Base case: if we've processed all columns
        if(idx >= grid[0].size()) return 0;

        // If result for current state is already computed, return it
        if(dp[idx][prev] != -1) return dp[idx][prev];

        int ans = INT_MAX;

        // Try changing current column to different values
        for(int i = 0 ; i <= 9 ; i++ ){
            if(i != prev){
                // grid.size() - cnt[{idx, i}]) is = All rows - freq(i) of current col index
                int current_operations = (int)(grid.size() - cnt[{idx, i}]) + helper(grid, cnt, idx + 1, i , dp);
                ans = min(ans , current_operations);
            }
        }

        // Memoize the result and return
        return dp[idx][prev] = ans;
    }
};