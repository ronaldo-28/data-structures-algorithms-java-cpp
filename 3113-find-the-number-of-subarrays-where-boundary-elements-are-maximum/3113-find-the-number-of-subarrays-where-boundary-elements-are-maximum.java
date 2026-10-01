class Solution {
    public long numberOfSubarrays(int[] nums) {
        long ans=0;
        int n=nums.length;
        int[] cnt=new int[n];
        int[] value=new int[n];

        int top=-1;

        for(int x : nums){

            while(top>=0 && value[top] < x){
                top--;
            }

            if(top>=0 && value[top]==x){
                cnt[top]++;
                ans+=cnt[top];
            }
            else{
                top++;
                value[top]=x;
                cnt[top]=1;
                ans++;
            }
        }



        return ans;
    }
}