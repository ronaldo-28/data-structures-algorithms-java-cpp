class Solution {
public:
    string greatestLetter(string s) {
        int up=0;
        int lo=0;

        for(char c:s) {
            if (c <= 'Z') {
                up |= 1<<(c-'A');
            } else {
                lo |= 1<<(c-'a');
            }
        }

        up = up & lo;

        for(int i=26; i>=0; i--) {
            if (up & (1<<i)) {
                return string(1, 'A'+i);
            }
        }

        return "";
    }
};