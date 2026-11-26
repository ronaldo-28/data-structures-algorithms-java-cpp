class Solution {
public:
    int earliestTime(vector<vector<int>>& tasks) {
        int row = tasks.size();
        int col = tasks[0].size();

        int mini = INT_MAX;

        for(int i = 0; i < row; i++) {
            int sum = 0;

            for(int j = 0; j < col; j++) {
                sum += tasks[i][j];
            }

            mini = min(mini, sum);
        }

        return mini;
    }
};