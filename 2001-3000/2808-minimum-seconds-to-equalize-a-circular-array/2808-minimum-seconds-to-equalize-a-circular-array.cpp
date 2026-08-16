class Solution {
public:
    int minimumSeconds(vector<int>& b) {
        int n = b.size(), ans = INT_MAX, d = 0, st;
        vector<pair<int, int>> a;

        for(int i = 0; i < n; ++i)
            a.push_back({ b[i], i });
        
        sort(a.begin(), a.end()), st = a[0].second;

        for(int i = 1; i < n; ++i)
        {
            if(a[i].first != a[i - 1].first)
            {
                d = max(d, st + n - 1 - a[i - 1].second), st = a[i].second;
                ans = min(ans, (d / 2) + (d % 2)), d = 0;
            }
            else
                d = max(d, a[i].second - a[i - 1].second - 1);
        }

        d = max(d, st + n - 1 - a[n - 1].second), ans = min(ans, (d / 2) + (d % 2));
        return ans;
    }
};