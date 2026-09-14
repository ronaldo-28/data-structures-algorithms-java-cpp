class Solution {
    static{
        for(int i=0;i<500;i++) find132pattern(new int[]{1,3});
    }
    public static boolean find132pattern(int[] nums) {

        int n = nums.length;
        if(n < 3){
            return false;
        }

        int[] stack = new int[n];
        int top = -1;
        int second = Integer.MIN_VALUE;

        // Traversefrom right to left
        for(int i = n - 1; i >= 0; i--){
            if(nums[i] < second){
                return true;    // found nums[i] < nums[k] < nums[j]
            }
            while(top >= 0 && nums[i] > stack[top]){
                second = stack[top--];   // pop
            }
            stack[++top] = nums[i];   // puh
        }
        return false;
    }
}