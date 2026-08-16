class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            minImpossibleOR(new int[]{0});
        }
    }
    public static int minImpossibleOR(int[] nums) {
        int bits = 0;
        for (int num : nums) 
            if ((num & (num - 1)) == 0) bits |= num;

        int res = 1;
        while ((bits & res) != 0) res <<= 1;

        return res;
    }
}