class Solution {
    public int alternatingSum(int[] nums) {
        int sum = 0;
        boolean even = true; // we just took a boolean variable to keep a track that whether the index is even or not, if even then add the number else subtract..
        for(int i = 0; i < nums.length; i++){
            if(even)
                sum = sum + nums[i];
            else sum = sum - nums[i];
            even = !even;
        }
        return sum;
    }
}