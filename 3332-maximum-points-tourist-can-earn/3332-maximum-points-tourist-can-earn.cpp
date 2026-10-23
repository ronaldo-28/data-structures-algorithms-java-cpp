#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int maxScore(int n, int k, vector<vector<int>>& stayScore, vector<vector<int>>& travelScore) {
        vector<long long> dp_prev(n, 0);
        for (int c = 0; c < n; ++c) dp_prev[c] = stayScore[0][c];
        for (int c = 0; c < n; ++c) {
            long long best = 0;
            for (int s = 0; s < n; ++s) best = max(best, (long long)travelScore[s][c]);
            dp_prev[c] = max(dp_prev[c], best);
        }
        for (int day = 1; day < k; ++day) {
            vector<long long> dp_curr(n, 0);
            for (int city = 0; city < n; ++city) {
                long long best_move = dp_prev[0] + travelScore[0][city];
                for (int p = 1; p < n; ++p) best_move = max(best_move, dp_prev[p] + travelScore[p][city]);
                long long stay = dp_prev[city] + stayScore[day][city];
                dp_curr[city] = max(stay, best_move);
            }
            dp_prev.swap(dp_curr);
        }
        long long ans = 0;
        for (int c = 0; c < n; ++c) ans = max(ans, dp_prev[c]);
        return (int)ans;
    }
};