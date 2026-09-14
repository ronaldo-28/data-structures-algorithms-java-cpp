class Solution {
public:
    int totalDistance(string s) {
        array<int, 26> row, col;
        row[16] = row[22] = row[4] = row[17] = row[19] = row[24] = row[20] = row[8] = row[14] = row[15] = 1;
        row[0] = row[18] = row[3] = row[5] = row[6] = row[7] = row[9] = row[10] = row[11] = 2;
        row[25] = row[23] = row[2] = row[21] = row[1] = row[13] = row[12] = 3;

        col[16] = col[0] = col[25] = 1;
        col[22] = col[18] = col[23] = 2;
        col[4] = col[3] = col[2] = 3;
        col[17] = col[5] = col[21] = 4;
        col[19] = col[6] = col[1] = 5;
        col[24] = col[7] = col[13] = 6;
        col[20] = col[9] = col[12] = 7;
        col[8] = col[10] = 8;
        col[14] = col[11] = 9;
        col[15] = 10;

        int res = abs(row[s[0] - 'a'] - row[0]) + abs(col[s[0] - 'a'] - col[0]);

        for (int i = 1; i < size(s); ++i) {
            int cur = s[i] - 'a', prev = s[i - 1] - 'a';
            res += abs(row[cur] - row[prev]) + abs(col[cur] - col[prev]);
        }

        return res;
    }
};