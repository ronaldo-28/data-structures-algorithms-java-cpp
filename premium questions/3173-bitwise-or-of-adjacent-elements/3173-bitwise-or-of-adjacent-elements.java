class Solution {
    public int[] orArray(int[] nums) {

        int [] answer = new int[nums.length-1] ;
        for(int i=1; i<nums.length ; i++){

            answer[i-1]=nums[i-1] | nums[i];
        }

        return answer;
        
    }
}