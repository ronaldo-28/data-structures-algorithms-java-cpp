const int kMod = 1000'000'007;

int ModAdd(int64_t a, int64_t b) {
  return (a + b) % kMod;
}

class Solution {
public:
  int countRoutes(vector<int>& locations, int start, int finish, int fuel) {
    int start_location = locations[start];
    int finish_location = locations[finish];
    std::sort(locations.begin(), locations.end());
    start = std::lower_bound(locations.begin(), locations.end(), start_location) - locations.begin();
    finish = std::lower_bound(locations.begin(), locations.end(), finish_location) - locations.begin();
    
    const int N = locations.size();
    const int M = fuel + 1;
    vector<vector<int>> dpR(N, vector<int>(M));
    vector<vector<int>> dpL(N, vector<int>(M));
    for (int x = 1; x < M; ++x) {
      for (int i = 1; i < N; ++i) {
        int y = x - (locations[i] - locations[i - 1]);
        if (y < 0) {
          continue;
        }
        dpR[i - 1][x] = ModAdd(dpR[i][y] * 2, dpL[i][y]);
        dpL[i][x] = ModAdd(dpL[i - 1][y] * 2, dpR[i - 1][y]);
        if (i == finish) {
          dpR[i - 1][x] = ModAdd(dpR[i - 1][x], 1);
        }
        if (i - 1 == finish) {
          dpL[i][x] = ModAdd(dpL[i][x], 1);
        }
      }
    }
    int answer = dpR[start][fuel] + dpL[start][fuel];
    if (start == finish) {
      ++answer;
    }
    return answer % kMod;
  }
};