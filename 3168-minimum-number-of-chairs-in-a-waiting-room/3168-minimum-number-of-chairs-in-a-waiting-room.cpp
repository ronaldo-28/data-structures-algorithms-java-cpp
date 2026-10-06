class Solution {
public:
    int minimumChairs(string s) {
        int su = 0;
        int ans = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s[i] == 'E')
                su++;
            else
                su--;

            ans = max(su, ans);
        }

        return ans;
    }
};