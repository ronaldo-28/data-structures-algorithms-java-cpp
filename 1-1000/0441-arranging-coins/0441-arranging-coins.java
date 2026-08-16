public class Solution {
    public int arrangeCoins(int n) {
        long k = (long)(Math.sqrt(2L * n + 0.25) - 0.5);
        return (int)k;
    }
}