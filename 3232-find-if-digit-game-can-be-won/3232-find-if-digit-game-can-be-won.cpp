class Solution {
public:
    bool canAliceWin(vector<int>& nums) {
        int count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num < 10) {
                count1 += num;
            } else {
                count2 += num;
            }
        }
        return count1 != count2;
    }
};