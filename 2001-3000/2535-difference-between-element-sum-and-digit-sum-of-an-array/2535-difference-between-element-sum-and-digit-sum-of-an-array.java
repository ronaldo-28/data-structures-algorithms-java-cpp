class Solution {
    public int differenceOfSum(int[] nums) {
        int result = 0;
        for(int v : nums) result += v - (v / 1000 + v / 100 % 10 + v % 100 / 10 + v % 10);
        return Math.abs(result);
    }
}