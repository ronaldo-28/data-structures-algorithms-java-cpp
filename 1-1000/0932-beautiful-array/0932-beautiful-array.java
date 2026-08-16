class Solution {
    public int[] beautifulArray(int n) {
        // Base case: if the array size is 1, return an array with a single element 1
        if (n == 1) {
            return new int[] {1};
        }
      
        // Recursively call the function for the first half, rounded up
        int[] leftHalf = beautifulArray((n + 1) >> 1);
      
        // Recursively call the function for the second half
        int[] rightHalf = beautifulArray(n >> 1);
      
        // Create an array to hold the beautiful array of size n
        int[] result = new int[n];
      
        int index = 0; // Initialize the index for the result array
      
        // Fill the result array with odd numbers by manipulating the left half
        for (int element : leftHalf) {
            result[index++] = element * 2 - 1;
        }
      
        // Fill the result array with even numbers by manipulating the right half
        for (int element : rightHalf) {
            result[index++] = element * 2;
        }
      
        // Return the compiled beautiful array
        return result;
    }
}