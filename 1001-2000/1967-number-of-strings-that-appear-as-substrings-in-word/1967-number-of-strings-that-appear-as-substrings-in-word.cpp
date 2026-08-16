class Solution {
public:
    int numOfStrings(vector<string>& patterns, string word) {
        int cnt = 0;
        int n = patterns.size();

        for (int i = 0; i < n; i++) {
            // check if pattern exists in word
            if (word.find(patterns[i]) != -1)
                cnt++;
        }

        return cnt;
    }
};