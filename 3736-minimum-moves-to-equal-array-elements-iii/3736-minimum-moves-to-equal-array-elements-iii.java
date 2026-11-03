class Solution {
    public int minMoves(int[] nums) {
        
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for(int num : nums){
            max = Math.max(max, num);
            sum += num;
        }

        return nums.length * max - sum;
    }
}