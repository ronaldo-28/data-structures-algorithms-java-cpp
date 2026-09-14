class Solution {
    public int minOperations(int[] nums, int x) {
        int l=0;
        int n=nums.length;
        int r=n-1;
        
        while(r>=0 && x>0){
            x-=nums[r];
            r--;
        }

        if(r<0){
            if(x==0)
             return n;
            else if(x>0)
            return -1;
        }
        
        int operations = Integer.MAX_VALUE;
        if(x==0){
            operations=n-r;
        }
        r++;
        // move the pointers towards right side
        while(r<n){
            if(x<0){
                x+=nums[r++];
            }else if(x>0){
                x-=nums[l++];
            }else{
                operations=Math.min(operations,l+(n-r));
                x+=nums[r];
                r++;
            }
        }

        while(l<n && x>0){
            x-=nums[l++];
        }

        if(x==0){
            operations=Math.min(operations,l+(n-r));
        }

        return operations==Integer.MAX_VALUE?-1:operations;


    }
}