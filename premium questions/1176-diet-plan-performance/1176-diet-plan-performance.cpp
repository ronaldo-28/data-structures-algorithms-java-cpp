class Solution {
public:
    int dietPlanPerformance(vector<int>& calories, int k, int lower, int upper) {
        int ans = 0, sum = 0;
        for (int i = 0; i < calories.size(); ++i){
            sum += calories[i];
            if (i >= k) sum -= calories[i - k];
            if (i >= (k - 1))
                ans += sum < lower ? -1 : sum > upper ? 1 : 0;
        }
        return ans;
    }
};