class Solution {
    public void swap(int[] nums,int i)
    {
        if(i==nums.length)
        {
            return;
        }
        int temp = nums[nums[i]];
        swap(nums,i+1);
        nums[i]=temp;
    }
    public int[] buildArray(int[] nums) 
    {
        swap(nums,0);
        return nums;
    }
}