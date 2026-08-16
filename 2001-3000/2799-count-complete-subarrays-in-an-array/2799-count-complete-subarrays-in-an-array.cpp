class Solution {
public:
    int countCompleteSubarrays(vector<int>& nums) {
        static constexpr const size_t theVals{2'001ull};
        int freq[theVals];
        namespace rng = std::ranges;
        rng::fill(freq,0);
        int theCnt{};
        for(int i: nums){
            ++freq[i];
            theCnt += !(1-freq[i]);
        }
        int i{},j{},ret{},c{};
        int dp[theVals]{};
        const auto n{static_cast<int>(ranges::size(nums))};
        while(i<n){
            ++dp[nums[i]];
            if(!(1-dp[nums[i]]))++c;
            while(c==theCnt){
                ret += n-i;
                --dp[nums[j]];
                if(!dp[nums[j++]])--c;
            }
            ++i;
        }
        return ret;
    }
};