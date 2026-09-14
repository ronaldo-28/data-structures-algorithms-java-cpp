class Solution {
public:
    long long minOperations(vector<int>& nums1, vector<int>& nums2, int k) {

        int s = nums1.size();
        long long diffSum = 0;
        long long sumPos = 0;

        long long diff;

        for (int i = 0; i < s; i++) {
            diff = nums1[i] - nums2[i];
            diffSum += diff;
            
            if(diff>0)sumPos+=diff;//taking sum of only pos values for final ans calculations

            if (k > 0 && diff % k != 0)//if not div by k not possible
                return -1;

            if (diff != 0 && k == 0)//if there is diff but k=0 not possible
                return -1;
        }
        if (diffSum != 0)//if overall diff !=0 not possible
            return -1;
        else if (k == 0)//if overall diff=0 and k=0 return 0 without division
            return 0;

        // ANS-->>
        
        return sumPos / k;
    }
};