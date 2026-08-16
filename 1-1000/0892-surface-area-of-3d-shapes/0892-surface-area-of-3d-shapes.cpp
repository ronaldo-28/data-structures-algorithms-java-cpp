class Solution {
public:
    int surfaceArea(vector<vector<int>>& grid) {
        int result{0};
        int directions[4][2] = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};

        for(int row{0}; row < grid.size(); ++row)
        {
            for(int column{0}; column < grid[row].size(); ++column)
            {
                if(grid[row][column] > 0)
                {
                    result += 2;
                    for(const auto& direction : directions)
                    {
                        int neighbor_row = row + direction[0];
                        int neighbor_column = column + direction[1];
                        int nv{0};
                        if(neighbor_row >= 0 && neighbor_row < grid.size() && neighbor_column >=0 && neighbor_column < grid[neighbor_row].size())
                        {
                            nv = grid[neighbor_row][neighbor_column];
                        }
                        result += std::max(grid[row][column] - nv, 0);
                    }
                }
            }
        }
        return result;
    }
};