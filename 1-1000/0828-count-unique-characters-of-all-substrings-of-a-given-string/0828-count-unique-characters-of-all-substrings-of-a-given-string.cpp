class Solution {
public:
    int uniqueLetterString(string s) {
        vector<int> last(26, -1);
        vector<int> prevLast(26, -1);
        int ans = 0;
        int sum = 0;
        int n = s.size();
        for(int i = 0; i < n; i ++) {
            int c = s[i] - 'A';
            sum += i - last[c];
            sum -= (last[c] - prevLast[c]);
            ans += sum;
            prevLast[c] = last[c];
            last[c] = i;
        }
        return ans;
    }
};