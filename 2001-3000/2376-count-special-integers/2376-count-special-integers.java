class Solution {
    
    /// Solution
    public int countSpecialNumbers(int n) {
        // potd.code.hub
        String number = Integer.toString(n);
        int len = number.length();
        int total = 0;
        int mul = 1;

        /*
            I divided the problem into 2 parts.
            for example if number is 48025 then
                part - 1  ---> 1 to 9999 (all 1-digit numbers + all 2-digit numbers +...+ all 4-digit numbers)
                part - 2  ---> 10000 to 48025 (remaining 5-digit numbers)

            I solved part - 1 using combinatorics formula and part - 2 using recursion
        */
        
        // part - 1
        for (int i = 0; i <= len - 2; i++) {
            int p = (i == 0) ? 9 : 10 - i;
            mul *= p;
            total += mul;
        }

        // part - 2
        return total + solve(0, 1, 0, len, number);
    }

    private int solve(int idx, int tight, int mask, int size, String number) {
        // base case
        if (idx == size) {
            return 1;
        }

        // recursive case
        if (tight == 0) {
            return (10 - idx) * solve(idx + 1, tight, mask, size, number);
        }

        int total = 0;
        int lb = (idx == 0) ? 1 : 0;
        int ub = number.charAt(idx) - '0';

        for (int i = lb; i <= ub; i++) {
            if ((mask & (1 << i)) != 0) continue;
            int newTight = (tight == 1 && i == ub) ? 1 : 0;
            int newMask = mask | (1 << i);
            total += solve(idx + 1, newTight, newMask, size, number);
        }

        return total;
    }
}