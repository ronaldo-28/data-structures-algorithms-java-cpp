class Solution {
public:
    int visibleMountains(vector<vector<int>>& peaks) {
        int n = peaks.size();

        vector<pair<int,int>> a;
        a.reserve(n);

        for (auto& p : peaks)
            a.emplace_back(p[0] - p[1], p[0] + p[1]);

        sort(a.begin(), a.end(), [](const auto& x, const auto& y) {
            if (x.first != y.first)
                return x.first < y.first;
            return x.second > y.second;
        });

        int ans = 0;
        int maxR = INT_MIN;

        for (int i = 0; i < n; ) {
            int j = i + 1;

            while (j < n && a[j] == a[i])
                ++j;

            // Not covered by an earlier mountain.
            if (a[i].second > maxR) {
                // Duplicate identical mountains are not visible.
                if (j - i == 1)
                    ++ans;

                maxR = a[i].second;
            }

            i = j;
        }

        return ans;
    }
};