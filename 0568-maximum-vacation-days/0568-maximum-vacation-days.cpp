class Solution {
public:
    int maxVacationDays(vector<vector<int>>& flights,
                        vector<vector<int>>& days) {

        int N = days.size(), K = days[0].size();

        int memo[101][101];
        for (int i = 0; i < N; ++i) {
            memo[i][K] = 0;
        };

        int conn[101][101];
        for (int i = 0; i < N; ++i) {
            int m = 0;
            for (int j = 0; j < N; ++j) {
                if (flights[i][j]) {
                    conn[i][++m] = j;
                }
            }
            conn[i][0] = m;
        }

        for (int j = K - 1; j >= 0; --j) {
            for (int i = 0; i < N; ++i) {
                memo[i][j] = days[i][j] + memo[i][j + 1];
                for (int l = 1, m = conn[i][0]; l <= m; ++l) {
                    int i2 = conn[i][l];
                    memo[i][j] = max(memo[i][j], days[i2][j] + memo[i2][j + 1]);
                }
            }
        }

        return memo[0][0];
    }
};