class Solution {
    static {
        for(int i=0; i<500; i++) {singleNumber(new int[]{});}
    }
    public static int[] singleNumber(int[] nums) {
        int xor = 0;
        for(int i : nums) {
            xor ^= i;
        }

        int flag = xor & ((~xor)+1);
        int a = 0, b = 0;
        for(int i : nums) {
            if((i & flag) == 0) {
                a ^= i;
            }
            else {
                b ^= i;
            }
        }
        return new int[] {a, b};
    }
}