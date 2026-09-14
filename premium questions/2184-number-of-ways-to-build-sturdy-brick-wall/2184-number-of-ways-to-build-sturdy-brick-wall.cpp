class Solution {
public:
    int buildWall(int height, int width, vector<int>& bricks) {
        constexpr static int M = 1000000007;

        sort(begin(bricks), end(bricks));

        vector<unsigned> masks;
        dfs(0, 0, width, bricks, masks);

        vector<vector<int>> compatibles(masks.size());
        for (size_t i = 0; i < masks.size(); i++) {
            for (size_t j = 0; j < masks.size(); j++) {
                if ((masks[i] & masks[j]) == 0) {
                    compatibles[i].emplace_back(j);
                }
            }
        }

        vector<int> last(masks.size(), 1), curr(masks.size());
        for (size_t i = 1; i < height; i++) {
            for (size_t j = 0; j < masks.size(); j++) {
                for (size_t k : compatibles[j]) {
                    curr[k] = (curr[k] + last[j]) % M;
                }
            }

            swap(curr, last);
            fill(begin(curr), end(curr), 0);
        }

        int result = 0;
        for (int n : last) {
            result = (n + result) % M;
        }
        return result;
    }

private:
    void dfs(unsigned curr_state, int curr_pos, int width, vector<int>& bricks, vector<unsigned>& result) {
        for (int brick : bricks) {
            if (curr_pos + brick == width) {
                result.emplace_back(curr_state);
            } else if (curr_pos + brick < width) {
                dfs(curr_state | (1 << (curr_pos + brick)), curr_pos + brick, width, bricks, result);
            } else {
                break;
            }
        }
    }
};