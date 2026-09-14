import java.util.Arrays;

class Solution {
    public int singleNumber(int[] nums) {
        return xor(0,nums);
    }
    static int xor(int n, int[] nums){
        if(n>=nums.length)return 0;
            return nums[n] ^ xor(n+1,nums);
    }
}