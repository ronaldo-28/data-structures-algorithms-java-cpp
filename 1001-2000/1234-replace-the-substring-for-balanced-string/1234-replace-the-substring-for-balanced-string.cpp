class Solution {
public:
    int balancedString(string s) {

        int n = s.size();
        int limit = n / 4;

        vector<int> freq(128, 0);

        for(char c : s)
            freq[c]++;

        // ⭐ already balanced
        if(freq['Q'] == limit &&
           freq['W'] == limit &&
           freq['E'] == limit &&
           freq['R'] == limit)
            return 0;

        int l = 0;
        int ans = n;

        for(int r = 0; r < n; r++) {

            freq[s[r]]--;

            while(l <= r &&
                  freq['Q'] <= limit &&
                  freq['W'] <= limit &&
                  freq['E'] <= limit &&
                  freq['R'] <= limit) {

                ans = min(ans, r - l + 1);

                freq[s[l]]++;
                l++;
            }
        }

        return ans;
    }
};