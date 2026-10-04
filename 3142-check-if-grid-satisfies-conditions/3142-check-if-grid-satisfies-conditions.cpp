class Solution {
public:
    bool satisfiesConditions(vector<vector<int>>& grid) {
        int r = grid.size();
        int c = grid[0].size();
        for (int i = 0; i < c; i++) {
            int down = grid[0][i];
            for (int j = 0; j < r; j++) {
                if (grid[j][i] != down)
                    return false;
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (j + 1 < c) {
                    if (grid[i][j] == grid[i][j + 1])
                        return false;
                }
            }
        }
        return true;
    }
};