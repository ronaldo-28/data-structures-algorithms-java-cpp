class Solution {
public:
    int findJudge(int n, vector<vector<int>>& trust) {
        int inDegree[1001]{}, outDegree[1001]{};
        for(const vector<int>& edge : trust) {
            ++outDegree[edge[0]];
            ++inDegree[edge[1]];
        }

        for(int i = 1; i <= n; ++i) {
            if(outDegree[i] == 0 && inDegree[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }
};