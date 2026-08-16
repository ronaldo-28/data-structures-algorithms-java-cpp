class Solution {
    public int addDigits(int num) {
        // Check if the number is 0
        if (num == 0) {
            return 0;
        }

        // The digital root can be found using the formula: 1 + (num - 1) % 9
        return 1 + (num - 1) % 9;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int num1 = 38;
        System.out.println(solution.addDigits(num1));  // Output: 2

        // Example 2
        int num2 = 0;
        System.out.println(solution.addDigits(num2));  // Output: 0
    }
}
