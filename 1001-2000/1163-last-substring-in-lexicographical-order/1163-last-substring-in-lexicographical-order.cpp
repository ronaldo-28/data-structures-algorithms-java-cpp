class Solution {
public:
    string lastSubstring(string s) {
        int i = 0, j = 1, k = 0;

        while (j + k < s.size()) {          
            if (s[i + k] == s[j + k]) {
                k++;
            }
            else if (s[i + k] > s[j + k]) {                
                j = j + k + 1;
                k = 0;
            }
            else {
                // if i+k surpass j
                if (i + k + 1 > j) {
                    // i = j + k - 1;
                    i = i + k + 1;
                } 
                else {
                    i = j;
                }

                j = i + 1;
                k = 0;
            }
        }

        return s.substr(i);
    }
};