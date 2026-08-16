class Solution {

    public boolean isPrime(int x) {
        if (x <= 1) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;

        for (int i = 3; i * i <= x; i += 2) {
            if (x % i == 0) return false;
        }
        return true;
    }

    // Function to reverse digits
    public int reverseNum(int n) {
        int r = 0;
        while (n > 0) {
            r = r * 10 + (n % 10);
            n /= 10;
        }
        return r;
    }

    public int sumOfPrimesInRange(int n) {
        int r = reverseNum(n);

        int low = Math.min(n, r);
        int high = Math.max(n, r);

        int sum = 0;

        for (int i = low; i <= high; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }

        return sum;
    }
}