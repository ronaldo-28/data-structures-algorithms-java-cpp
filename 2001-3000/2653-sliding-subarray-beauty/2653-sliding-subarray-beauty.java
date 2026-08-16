class Solution {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int i=0;
        int j=0;
        int[] ans=new int[nums.length-k+1];
        int count=0;
        int[] freq=new int[51];
        int idx=0;
        while(j<nums.length){
            if(nums[j]<0){
                count++;
                freq[Math.abs(nums[j])]++;
            }
            if(j-i+1==k){
                if(count<x){ 
                    ans[idx++]=0;
                    }
                else{
                int c=0;
                for(int m=50;m>=1;m--){
                    c+=freq[m];
                    if(c>=x){
                        ans[idx++]=-m;
                        break;
                    }
                }
                }
                if(nums[i]<0){
                    count--;
                    freq[Math.abs(nums[i])]--;
                }
                i++;
            }
            j++;
        }
        return ans;
    }
}