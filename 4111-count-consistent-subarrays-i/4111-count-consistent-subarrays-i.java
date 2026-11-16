class Solution {
    public int countConsistent(int[] nums) {
        int n = nums.length;
        int count = 0;

        for(int i = 0; i < n; i++){
            int min = Integer.MAX_VALUE;
            int AND = -1;

            for(int j = i; j < n; j++){
                min = Math.min(min, nums[j]);
                AND &= nums[j];

                if(AND == min){
                    count++;
                }
            }
        }

        return count;
    }
}