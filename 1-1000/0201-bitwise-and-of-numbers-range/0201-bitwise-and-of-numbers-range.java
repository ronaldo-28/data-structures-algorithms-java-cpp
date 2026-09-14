public class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;

        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }

        return left << shift;
    }

    public static void main(String[] args) {
        // Example usage:
        Solution solution = new Solution();
        System.out.println(solution.rangeBitwiseAnd(5, 7));          // Output: 4
        System.out.println(solution.rangeBitwiseAnd(0, 0));          // Output: 0
        System.out.println(solution.rangeBitwiseAnd(1, 2147483647)); // Output: 0
    }
}
