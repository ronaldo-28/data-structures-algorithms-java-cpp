class Solution {
public:
    int minimumDeviation(vector<int>& nums) {
        int n=nums.size();
        int span=0;
        for (int i=0; i<n; i++){
            if (nums[i]%2==1) nums[i]=2*nums[i];
            span=max(span,31-__builtin_clz(nums[i])-__builtin_ctz(nums[i]));
        }
        for (int i=0; i<n; i++){
            int leftmost=31-__builtin_clz(nums[i]);
            if (leftmost>span) nums[i]=nums[i]>>(leftmost-span);
        }
        sort(nums.begin(),nums.end());
        int i=n-1;
        while (nums[i]%2==0){
            i--;
        }
        int ans=nums[n-1]-nums[0];
        int j=n-1; int m=nums[0];
        for (int j=n-1; j>i; j--){
            nums[j]=nums[j]/2;
            m=min(m,nums[j]);
            ans=min(ans,nums[j-1]-m);
        }
        return ans;
        
    }
};