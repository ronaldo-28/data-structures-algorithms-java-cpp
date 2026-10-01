class Solution {
public:
    //foxed
    
    int dp[10][10000];
    int prefix[10001][20];

    int solve(int index, int ind, vector<int>& nums, vector<int>& andV)
    {
       if(index==nums.size())
       {
        if(ind == andV.size() )return 0;
        return 1e9;
       }
       if(ind == andV.size())
       {
         if( (nums[index]& andV[ind-1])  == andV[ind-1])
         return solve(index+1, ind, nums, andV) - nums[index-1]+nums[index];

         else return 1e9;
       }

       if(dp[ind][index]!=-1)
       return dp[ind][index];
       
       int mini= 1e9;

       
       if( ind>0 && (andV[ind-1] == (andV[ind-1] & nums[index])) )
       mini= min(mini, solve(index+1, ind, nums, andV)+ nums[index]- nums[index-1]);


       int low=index+1, high=nums.size();
       int l=high+1;

       while(low<=high){
          int mid= (low+high)>>1;
          int val=0;

          for(int bit=0; bit<20; bit++)
          {
             if(prefix[mid][bit]-prefix[index][bit] == mid-index)
             val |=(1<<bit);
          }

          if(val < andV[ind])
           high=mid-1;
          else if(val > andV[ind])
          low=mid+1;
          else{
            l= min(l,mid);
            high=mid-1;
          }
       }
       
       if(l<nums.size()+1)
       {
         mini= min(mini, solve(l, ind+1, nums, andV) + nums[l-1]);
       }

       return dp[ind][index]= mini;
    }

    int minimumValueSum(vector<int>& nums, vector<int>& andValues) {
        
        int n= nums.size();

        memset(prefix, 0, sizeof(prefix));

        for(int i=1; i<=n; i++)
        {
            for(int bit=0; bit<20; bit++)
            {
                if(nums[i-1]&(1<<bit))
                prefix[i][bit]++;
            }
            for(int bit=0; bit<20; bit++)
            prefix[i][bit]+= prefix[i-1][bit];
        }

        memset(dp, -1, sizeof(dp));

        int fox= solve(0,0,nums,andValues);

        if(fox==1e9)
        return -1;
        return fox;
    }
};