class Solution {
    public int monotoneIncreasingDigits(int n) {
        int multiplier = 10, decreasedN = n;
        while (!isMonotone(decreasedN)) {
            decreasedN = n - (n % multiplier + 1);  
            multiplier *= 10;
        }
        return decreasedN;
    }

    private boolean isMonotone(int n) {
        int prev = Integer.MAX_VALUE, current;
        while (n > 0) {
            current = n % 10;
            if (current > prev) {
                return false;
            }
            prev = current;
            n = n / 10;
        }
        return true;
    }
}