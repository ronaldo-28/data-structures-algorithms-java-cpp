class Solution {
public:
    int minRectanglesToCoverPoints(const vector<vector<int>>& points, int w) {
        const int n = points.size();

        vector<int> xvalue(n);
        for (int i = 0; i < n; ++i) {
            xvalue[i] = points[i][0];
        }
        sort(xvalue.begin(), xvalue.end());
        
        int rect = 0, covered = -1;
        for (int i = 0; i < n; ++i) {
            if (xvalue[i] <= covered) continue;

            covered = xvalue[i] + w;
            ++rect;
        }

        return rect;
    }
};