class Solution {
    public int minOperations(int[] nums, int[] numsDivide) {
        int factor = numsDivide[0];
        for(int x : numsDivide) {
            while(x > 0) {
                int temp = x;
                x = factor % x;
                factor = temp;
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int x : nums) {
            if(x < min && factor % x == 0) min = x;
        }
        if(min == Integer.MAX_VALUE) return -1;

        int count = 0;
        for(int x : nums) {
            if(x < min) count++;
        }
        return count;
    }
}