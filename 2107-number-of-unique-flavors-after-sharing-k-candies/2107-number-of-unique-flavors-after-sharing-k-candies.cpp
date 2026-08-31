class Solution {
public:
    int m1[100001];
    int m2[100001];
    int shareCandies(vector<int>& candles, int k) {

        // unordered_map<int,int> m1;
        // unordered_map<int,int> m2;
        int tot =0;
        for (int i : candles) {
            if (!m1[i])
                tot++;
            m1[i]++;
        }

        int ans = 0;
        for (int i = 0; i < candles.size(); i++) {
            if (i >= k) {
                if (m1[candles[i - k]] == m2[candles[i - k]]) {
                    tot++;
                }
                m2[candles[i - k]]--;
            }
            m2[candles[i]]++;
            if (m1[candles[i]] == m2[candles[i]])
                tot--;
            if (i + 1 >= k)
                ans = max(ans, tot);
        }
        return ans;
    }
};