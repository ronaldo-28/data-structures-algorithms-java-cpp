class Solution {
public:
    long long maxKelements(vector<int>& nums, int k) {
        long long sc = 0;
        vector<int> arr;
        sort(nums.begin(), nums.end());
        int n = nums.size(), ind = -1, i = n - 1, ops = k;
        while(ops--) {
            // cout<<i<<" ";
            // if(i >= 0)
            //     cout<<nums[i]<<" ";
            // cout<<ind<<" ";
            // if(ind != -1)
            //     cout<<arr[ind]<<" ";
            // cout<<endl;
            if(i >= 0 && (ind == -1 || arr[ind] < nums[i])) {
                sc += nums[i];
                int newn = nums[i] / 3;
                if(nums[i] % 3) newn++;
                arr.push_back(newn);
                i--;
                if(ind == -1)
                    ind++;
            } else {
                sc += arr[ind];
                int newn = arr[ind] / 3;
                if(arr[ind] % 3) newn++;
                arr.push_back(newn);
                ind++;
            }
        }
        return sc;
    }
};