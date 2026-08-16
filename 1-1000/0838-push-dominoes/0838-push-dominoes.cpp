class Solution {
public:
    string pushDominoes(string s) {
        int n = s.size();
        string t = "L" + s + "R";  
        int i = 0;                

        for (int j = 1; j < n + 2; j++) {
            if (t[j] == '.') continue;

            int dots = j - i - 1; 

            if (t[i] == t[j]) {
                for (int k = i + 1; k < j; k++)
                    t[k] = t[i];
            }
            else if (t[i] == 'R' && t[j] == 'L') {
                int l = i + 1, r = j - 1;
                while (l < r) {
                    t[l++] = 'R';
                    t[r--] = 'L';
                }
            }

            i = j; 
        }

        return t.substr(1, n); 
    }
};