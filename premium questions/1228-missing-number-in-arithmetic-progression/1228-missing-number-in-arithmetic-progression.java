class Solution {
    public int missingNumber(int[] arr) {
        int n = arr.length;
        
        // 1. A complete sequence will have exactly (n + 1) total numbers
        int totalTerms = n + 1;
        
        // 2. Standard math formula to find the sum of a full sequence
        int expectedSum = (arr[0] + arr[n - 1]) * totalTerms / 2;
        
        // 3. Add up all the numbers currently present in our array
        int actualSum = 0;
        for (int num : arr) {
            actualSum += num;
        }
        
        // The difference is exactly our missing number!
        return expectedSum - actualSum;
    }
}