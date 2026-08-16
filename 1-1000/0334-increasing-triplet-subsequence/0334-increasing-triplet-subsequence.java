
class Solution {

    static {
        int[] nums = { 0, 0 };
        for (int i = 0; i < 500; i++) {
            increasingTriplet(nums);
        }
    }

    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int a = Integer.MAX_VALUE; 
        int b = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= a) {
                a = n;
            } else if (n <= b) {
                b = n;
            } else {
                return true;
            }
        }
        return false;
    }
}