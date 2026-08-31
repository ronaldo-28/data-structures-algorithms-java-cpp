class Solution {
public:
    vector<int> amountPainted(vector<vector<int>>& paint) {
        // Find max endpoint
        int maxEnd = 0;
        for (auto& p : paint) maxEnd = max(maxEnd, p[1]);
        vector<int> out;
        
        int N = maxEnd;
        vector<int> dp(N+1,0);
        int n = paint.size();
        for(int i=0;i<n;i++) {
            int start = paint[i][0];
            int end = paint[i][1];
            int curr =start;
            int count = 0;
            while(curr < end) {
                while(curr <end && dp[curr] !=0) {
                    int next = dp[curr];
                    dp[curr] =max(end,dp[curr]);
                    curr = next;
                }
                if(curr <end && dp[curr] == 0) {
                    dp[curr] = end;
                    count++;
                    curr++;
                }
            }
            out.push_back(count);

        }
        return out;
    }
};