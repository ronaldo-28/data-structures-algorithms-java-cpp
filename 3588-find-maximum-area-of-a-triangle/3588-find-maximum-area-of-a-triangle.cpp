typedef long long ll;
class Solution {
public:
    ll maxArea(vector<vector<int>>& coords) {
        unordered_map<ll, pair<ll, ll>> mpx;
        unordered_map<ll, pair<ll, ll>> mpy;
        ll ymax = INT_MIN, ymin = INT_MAX, xmax = INT_MIN, xmin = INT_MAX;
        
        for (auto& it : coords) {
            ll x = it[0], y = it[1];

            if (mpx.find(x) == mpx.end()) mpx[x] = {INT_MAX, INT_MIN};
            mpx[x].first = min(mpx[x].first, y);
            mpx[x].second = max(mpx[x].second, y);

            if (mpy.find(y) == mpy.end()) mpy[y] = {INT_MAX, INT_MIN};
            mpy[y].first = min(mpy[y].first, x);
            mpy[y].second = max(mpy[y].second, x);

            xmax = max(xmax, x);
            xmin = min(xmin, x);
            ymax = max(ymax, y);
            ymin = min(ymin, y);
        }

        ll res = 0;

        for (auto& [x, p] : mpx) {
            ll base = abs(p.first - p.second);
            ll height = max(abs(x - xmax), abs(x - xmin));
            res = max(res, base * height);
        }

        for (auto& [y, p] : mpy) {
            ll base = abs(p.first - p.second);
            ll height = max(abs(y - ymax), abs(y - ymin));
            res = max(res, base * height);
        }

        return res == 0 ? -1 : res;
    }
};