class Solution {
    public boolean isGoodArray(int[] nums) {
        int  a = nums[0];
        for (int i = 0;i < nums.length;i++){
            int b = nums[i];
            while (a > 0){
                int t = a;
                a = b % a;
                b = t;
            }
            a = b;
        }
        return a == 1;
    }
}