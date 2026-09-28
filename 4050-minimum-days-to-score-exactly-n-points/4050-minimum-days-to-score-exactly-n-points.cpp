class preCompute {
public:
    vector<int> ans;

    preCompute() {

        ans.resize(1e5 + 1, 1e9);
        ans[0] = -1;

        for (int i = 1; i <= 1e5; i++) {

            int sum = 0;

            for (int k = 1; sum <= i; k++) {

                sum = sum + k;

                if (i - sum >= 0) {
                    ans[i] = min(ans[i], 1 + k + ans[i - sum]);
                }
            }
        }
    }
}OBJ;

class Solution {
public:
    int minDays(int n) {
        return OBJ.ans[n];
    }
};