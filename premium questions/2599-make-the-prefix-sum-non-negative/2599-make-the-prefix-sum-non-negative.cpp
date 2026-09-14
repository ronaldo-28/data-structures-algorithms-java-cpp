class Solution {
public:
    int makePrefSumNonNegative(vector<int>& nums) {
        std::priority_queue<int, std::vector<int>, std::greater<>> pq;

        long long sum {};

        int n {static_cast<int>(nums.size())};
        int ops{};
        for(int i =0 ; i <n ; i++){
            sum += nums[i];

            if (nums[i] < 0)
                pq.push(nums[i]);

            if (sum < 0){
                sum -= pq.top();
                pq.pop();
                ops++;
            }
        }
        return ops;      
    }
};