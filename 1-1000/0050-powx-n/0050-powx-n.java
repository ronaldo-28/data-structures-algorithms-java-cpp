class Solution {
    public double myPow(double x, int n) {
        // base cases
        if (n == 0) return 1.0; // x^0 = 1
        if (n == 1) return x; // x^1 = x
        if (n == -1) return 1.0 / x; // x^-1 = 1/x
        // general case
        if (n % 2 == 0) {
            // x^n = (x^2)^(n/2) when n is even
            return myPow(x * x, n / 2);
        } else {
            // x^n = x * (x^2)^((n-1)/2) when n is odd
            return x * myPow(x * x, (n - 1) / 2);
        }
    }
}
