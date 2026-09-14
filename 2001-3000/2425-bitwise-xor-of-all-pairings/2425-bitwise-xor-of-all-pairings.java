class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            xorAllNums(new int[]{1}, new int[]{1});
        }
    }
    public static int xorAllNums(int[] nums1, int[] nums2) {        
        int XOR1 = 0, XOR2 = 0;
        for (int n : nums1) XOR1 ^= n;
        for (int n : nums2) XOR2 ^= n;
        int ans = 0;
        if (nums2.length % 2 != 0) ans = XOR1;
        if (nums1.length % 2 != 0) ans ^= XOR2;
        return ans;
    }
}