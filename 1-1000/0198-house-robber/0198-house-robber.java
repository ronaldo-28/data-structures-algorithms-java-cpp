class Solution {
     int[] arr;

    int houseRobber(int n, int[] nums){
        if(n<0){
            return 0;

        }
        if(arr[n]>-1){
            return arr[n];
        }
        int maxTillNow = houseRobber(n-2, nums)+nums[n];
        int maxTill = houseRobber(n-1,nums);

        
        return arr[n] = Math.max(maxTillNow, maxTill);
    }
    public int rob(int[] nums) {
        //2,7,9,3,1
        //
        arr = new int[nums.length];
         Arrays.fill(arr, -1);
       return houseRobber(nums.length-1, nums);
    }
}