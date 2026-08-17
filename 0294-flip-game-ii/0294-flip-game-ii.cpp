class Solution {
public:
    bool canWin(string currentState) {
        int maxRun = 0;
        int run = 0;

        for (char c : currentState) {
            if (c == '+') {
                run++;
                maxRun = max(maxRun, run);
            } else {
                run = 0;
            }
        }

        vector<int> grundy(maxRun + 1, 0);

        for (int len = 2; len <= maxRun; len++) {
            unordered_set<int> reachable;

            for (int i = 0; i + 1 < len; i++) {
                int left = grundy[i];
                int right = grundy[len - i - 2];

                reachable.insert(left ^ right);
            }

            int mex = 0;

            while (reachable.count(mex)) {
                mex++;
            }

            grundy[len] = mex;
        }

        int total = 0;
        run = 0;

        for (int i = 0; i <= currentState.size(); i++) {
            if (i < currentState.size() && currentState[i] == '+') {
                run++;
            } else {
                total ^= grundy[run];
                run = 0;
            }
        }

        return total != 0;
    }
};