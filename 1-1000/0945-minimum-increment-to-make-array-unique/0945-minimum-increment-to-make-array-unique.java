class Solution {
    public int minIncrementForUnique(int[] nums) {
        int max = nums[0];
        for(int i=0;i<nums.length;i++){
            if(nums[i] > max)
            max = nums[i];
        }

        int arr[] = new int[nums.length + max + 1];

        for(int i=0;i<nums.length;i++){
            arr[nums[i]]+=1;
        }

        int count = 0;
        for(int i=0;i<arr.length - 1;i++){
            if(arr[i] == 0)
            continue;

            int d = arr[i] - 1;
            arr[i + 1] += d;
            count+=d;
        }
        return count;
    }

}