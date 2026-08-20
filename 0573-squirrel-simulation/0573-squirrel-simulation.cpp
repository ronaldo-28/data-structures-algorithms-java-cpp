class Solution {
public:
    int minDistance(int height, int width, vector<int>& tree, vector<int>& squirrel, vector<vector<int>>& nuts) {
        int res = INT_MAX, n = nuts.size(), tot_dist = 0;
        for (int i = 0; i < n; i++)
            tot_dist += 2 * getDistance(nuts[i], tree);
        for (int i = 0; i < n; i++)
            res = min(res, tot_dist - getDistance(nuts[i], tree) + getDistance(nuts[i], squirrel));
        return res;
    }
    
    int getDistance(vector<int>& a, vector<int>& b) {
        return abs(a[0] - b[0]) + abs(a[1] - b[1]);
    }
};