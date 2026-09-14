class Solution {
    public int[] reverseSubarrays(int[] nums, int k) {
        int result[]=new int[nums.length];
        int x=nums.length/k;
        int i=0,j=x-1,p=x-1;
        while(i<nums.length)
        {
            for(int y=0;y<x;y++)
            {
                result[i]=nums[j];
                j--;
                i++;
            }
            p=p+x;
            j=p;
        }

        return result;
    }
}