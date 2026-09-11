class Solution {
    static final int MOD = 1_000_000_007;
    public int countOfArrays(int n, int m, int k) {
        // n-length arrays made of the range [1,M] with k indicies where (arr[i] * arr[i + 1]) - arr[i] - arr[i + 1] is even
        // the function that must be even requires that the multiplication (arr[i] * arr[i+1]) and the comp subtraction (arr[i] - arr[i+1]):: odd - odd = even, even - even = even, odd - even = odd
        // Multiplication: odd x even = even, even x even = even, odd x odd = odd

        //So given index i and i+1 are odd (odd * odd = odd) - (odd - odd == EVEN) == odd - even = odd (NOT GOOD)

        // both even: (even * even == even) - (even - even == even) even - even = even (GOOD)

        // one odd one even: (odd * even == even ) - (odd - even == odd): even - odd == ODD

        // K elements must be EVEN next to EVEN if k=2 [2,2,2] [4,4,4] if n > k + 1 fill the rest with anything

        //Given this, we need only to know the parity of the previous number to decide what numbers are valid at index i (K TIMES)... the rest can be filled in any manner?

        /*
            For example 1: n=3, m=4, k=2 m:{1,2,3,4}
            try placing K+1 Evens: [2,4,2] where index 0 and 1 are the keven elements

            Reframe the question to how many permutations per proven k-even array are there
            Need to know: how many even numbers are in range [1,m] given that how many permutations of the array [even,even,even,..] are there and note that for indicies > k + 2 must either be odd or a mix but never two+ evens in a row. k + 2 must be odd since k + 1 is even 
        */

        final long E = m / 2;
        final long O = m - E;

       long[][][] dp = new long[n][k+1][2]; // [index][groupsMade][lastNumber is even(0) or odd(1)]
        dp[0][0][0] = E; // We could have put any even number at index 0
        dp[0][0][1] = O; // We could have put any odd number at index 0

        for (int i = 1; i < n; i++) { // For this index
            for (int j = 0; j <= k; j++) { // For this many groups made
                for (int p = 0; p < 2; p++) { // For this previous parity
                    long prev = dp[i-1][j][p];
                    if (prev == 0) continue;
                    // Place an odd
                    dp[i][j][1] = (dp[i][j][1] + prev * O) % MOD;
                    //Place an even
                    if (p == 0) {
                        if (j < k) dp[i][j+1][0] = (dp[i][j+1][0] + prev * E) % MOD;
                    } else {
                        dp[i][j][0] = (dp[i][j][0] + prev * E) % MOD;
                    }
                }
            }
        }
        return (int)(dp[n-1][k][0] + dp[n-1][k][1]) % MOD;
    }
}