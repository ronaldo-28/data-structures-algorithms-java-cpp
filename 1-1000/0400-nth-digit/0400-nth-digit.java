class Solution {
    public int findNthDigit(int n) {
        // Initialize digit length `k` for numbers of k digits
        // Initialize count `digitCount` for the count of numbers with `k` digits
        int digitLength = 1;
        int digitCount = 9;
      
        // Determine the range where the nth digit lies
        while ((long) digitLength * digitCount < n) {
            n -= digitLength * digitCount; // Reduce n by the number of positions we've covered
            digitLength++;                 // Move to next digit length
            digitCount *= 10;              // Increase the count for the next range of numbers
        }
      
        // Calculate the actual number where the nth digit is from
        int number = (int) Math.pow(10, digitLength - 1) + (n - 1) / digitLength;
      
        // Calculate the index within the number where the nth digit is located
        int digitIndex = (n - 1) % digitLength;
      
        // Extract and return the nth digit from number
        return String.valueOf(number).charAt(digitIndex) - '0';
    }
}