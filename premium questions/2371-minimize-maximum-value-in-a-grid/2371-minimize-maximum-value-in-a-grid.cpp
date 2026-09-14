/*
Because the values in the grid are strictly distinct, we never have to worry about assigning two cells the exact same value to maintain equality. We only care about < and >.

To minimize the maximum value, we should assign numbers starting from 1 and work our way up. If we process the grid elements from smallest to largest, we guarantee that when we assign a value to a cell, every smaller element in its row and column has already been processed and assigned its minimal valid value.

For any cell (r, c), its new value just needs to be 1 greater than the largest value we've assigned so far in row r and column c.
*/

#include <vector>
#include <algorithm>
#include <tuple>

using namespace std;

class Solution {
public:
    vector<vector<int>> minScore(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        
        // 1. Extract all elements with their coordinates
        // Using a flat array of tuples for easy sorting
        vector<tuple<int, int, int>> cells;
        cells.reserve(m * n);
        
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                cells.push_back({grid[r][c], r, c});
            }
        }
        
        // 2. Sort elements by value in ascending order
        sort(cells.begin(), cells.end());
        
        // 3. Track the maximum compressed value assigned in each row and column
        vector<int> row_max(m, 0);
        vector<int> col_max(n, 0);
        
        // 4. Process from smallest to largest
        for (const auto& [val, r, c] : cells) {
            // The new value must be strictly greater than the max seen in its row and col
            int new_val = max(row_max[r], col_max[c]) + 1;
            
            // Mutate the original grid to save space
            grid[r][c] = new_val;
            
            // Update the running maximums
            row_max[r] = new_val;
            col_max[c] = new_val;
        }
        
        return grid;
    }
};