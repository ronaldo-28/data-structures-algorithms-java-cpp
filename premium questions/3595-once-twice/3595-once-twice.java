class Solution {
    public int[] onceTwice(int[] nums) {
        int a = ~0, b = 0, c = 0;
        for(int num : nums) {
            int mask1 = num & a, mask2 = num & b, mask3 = num & c;
            a = a ^ mask1 | mask3;
            b = b ^ mask2 | mask1;
            c = c ^ mask3 | mask2;
        }
        int x = ~0, y = 0, z = 0;
        for(int num : nums) {
            if((num & c) != 0 || (num & b) != b) continue;
            int mask1 = num & x, mask2 = num & y, mask3 = num & z;
            x = x ^ mask1 | mask3;
            y = y ^ mask2 | mask1;
            z = z ^ mask3 | mask2;
        }
        return new int[] {y, y ^ b | c};
    }
}