class Solution {
public:
    int duplicateNumbersXOR(vector<int>& nums) {
        int ans = 0;
        long long seen = 0; // Our 64-bit "Hash Set"

        for(int num : nums) {
            // Check if the 'num'-th bit is already 1
            if((seen >> num) & 1) {
                ans = ans ^ num;
            } else {
                // Turn on the 'num'-th bit
                seen = seen | (1LL << num);
            }
        }
        
        return ans;
    }
};