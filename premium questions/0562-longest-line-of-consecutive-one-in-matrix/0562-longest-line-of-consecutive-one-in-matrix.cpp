#include <vector>
#include <utility>

using namespace std;

class Solution {
public:
    int longestLine(vector<vector<int>>& mat) {
        int m = mat.size();
        int n = mat[0].size();
        
        static const vector<int> moves = {-1, 0, 1};
        
        vector<int> prevV(n + 2, 0);
        vector<int> prevD(n + 2, 0);
        vector<int> prevA(n + 2, 0);
        
        vector<int> currV(n + 2);
        vector<int> currD(n + 2);
        vector<int> currA(n + 2);
        
        int maxLine = 0;
        for (int i = 0; i < m; i++) {
        int h = 0;
            fill(currV.begin(), currV.end(), 0);
            fill(currD.begin(), currD.end(), 0);
            fill(currA.begin(), currA.end(), 0);
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) { h = 0; continue; }
                h++;
                currV[j + 1] = prevV[j + 1] + 1;        // [i-1][j]
                currD[j + 1] = prevD[j]     + 1;        // [i-1][j-1]
                currA[j + 1] = prevA[j + 2] + 1;        // [i-1][j+1]
                maxLine = max({maxLine, h, currV[j + 1], currD[j + 1], currA[j + 1]});
            }
            
            swap(currV, prevV);
            swap(currD, prevD);
            swap(currA, prevA);
        }
        
        return maxLine;
    }
};