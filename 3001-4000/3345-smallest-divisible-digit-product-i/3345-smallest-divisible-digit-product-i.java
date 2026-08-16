class Solution {
    private int porductOfDigits(int num) {
        int result = 1;
        while(num > 0) {
            int digit = num % 10;
            num /= 10;
            result *= digit;  
        }

        return result;
    }
    public int smallestNumber(int n, int t) {
        int prod = 0;
        for(int i = 0; i < 10; i++) {
            prod = porductOfDigits(n);
            if(prod % t == 0) {
                return n;
            }
            n++;
        }

        return n;
    }
}