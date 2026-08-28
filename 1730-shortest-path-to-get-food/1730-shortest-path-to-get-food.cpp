class Solution {
public:
    int getFood(vector<vector<char>>& grid) {
        int m = grid.size();
        int n = grid[0].size();

        int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        queue<pair<int, int>> q;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '*') {
                    grid[i][j] = 'X';
                    q.push({i, j});
                    break;
                }
            }
            if (!q.empty())
                break;
        }

        int steps = 0;

        while (!q.empty()) {
            int size = q.size();

            while (size--) {
                auto [x, y] = q.front();
                q.pop();

                for (int i = 0; i < 4; ++i) {
                    int nx = x + dir[i][0];
                    int ny = y + dir[i][1];

                    if (nx < 0 || ny < 0 || nx >= m || ny >= n ||
                        grid[nx][ny] == 'X') {
                        continue;
                    }

                    if (grid[nx][ny] == '#') {
                        return steps + 1;
                    }

                    q.push({nx, ny});
                    grid[nx][ny] = 'X';
                }
            }

            ++steps;
        }

        return -1;
    }
};