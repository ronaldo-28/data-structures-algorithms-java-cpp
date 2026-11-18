
class Solution {
public:
    int minMoves(int sx, int sy, int tx, int ty) {
        int ans = 0;

        while (tx >= sx && ty >= sy) {
            if (tx == sx && ty == sy)
                return ans;

            if (tx == ty) {
                if (tx == 0) return -1;

                if (sx == 0 && sy > 0) {
                    tx = 0;
                } else if (sy == 0 && sx > 0) {
                    ty = 0;
                } else {
                    return -1;
                }
            } else if (tx > ty) {
                if (tx >= 2LL * ty) {
                    if (tx & 1) return -1;
                    tx /= 2;
                } else {
                    tx -= ty;
                }
            } else {
                if (ty >= 2LL * tx) {
                    if (ty & 1) return -1;
                    ty /= 2;
                } else {
                    ty -= tx;
                }
            }

            ++ans;
        }

        return -1;
    }
};