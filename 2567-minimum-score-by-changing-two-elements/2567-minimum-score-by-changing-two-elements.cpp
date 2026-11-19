class Solution {
public:
    int minimizeSum(vector<int>& nums) {
        int s1 = INT_MAX, s2 = INT_MAX, s3 = INT_MAX, l1 = 0, l2 = 0, l3 = 0;
        for(auto n: nums){
            if(s1 > n) { s3 = s2; s2 = s1; s1 = n; }
            else if(s2 > n) { s3 = s2; s2 = n; }
            else if(s3 > n) { s3 = n; }

            if(l1 < n) { l3 = l2; l2  = l1; l1 = n; }
            else if(l2 < n ) { l3 = l2; l2 = n; }
            else if(l3 < n ) { l3 = n; }
        }
        return min({l1 - s3, l3 - s1, l2 - s2});
    }
};