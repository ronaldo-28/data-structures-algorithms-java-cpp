class Solution {
    public int getSum(int a, int b) {
         while (b != 0) {
           int carry = a & b; // Calculate the carry bits
           a = a ^ b;      // Calculate sum without carry bits
           b = carry << 1;  // Shift carry bits to the left
         }
       return a;
    }
}