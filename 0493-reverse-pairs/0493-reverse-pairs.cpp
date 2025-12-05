class Solution {
public:
    int reversePairs(vector<int>& nums) {
        int n = nums.size();
        long long reversePairsCount = 0;
        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                if(nums[i] > 2*(long long)nums[j]){
                    reversePairsCount++;
                }
            }
        }
        return reversePairsCount;
    }
};






/*

    Time Complexity : O(NlogN), Each recursive call to performs two recursive calls on subslices of size N/2 and
    One linear scans of length <= N. Therefore, the time complexity of the divide & conquer approach can be
    represented by the following recurrence relation: T(N)=2T(N/2)+N. Where N is the size of the Array(nums).

    Space Complexity : O(N), Recursion Stack Space O(logN) + Array(temp) space O(N). 

    Solved using Array + Divide and Conquer + Merge Sort. Optimized Approach.

*/