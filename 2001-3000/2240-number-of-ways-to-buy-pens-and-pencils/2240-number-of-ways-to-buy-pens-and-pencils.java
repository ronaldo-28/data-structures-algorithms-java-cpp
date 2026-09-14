class Solution {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long n = total / cost1;
        long a = cost1;
        long b = total % cost1;
        long c = cost2;
        
        // Final answer is the floor sum + (n + 1) because for each 'i' 
        // we added +1 in the original loop logic (buying 0 minor items).
        return floorSum(n, a, b, c) + (n + 1);
    }
    
    // Standard Floor Sum Algorithm / Like-Euclidean Algorithm
    // Calculates: Sum of floor((a*i + b)/c) for i from 0 to n
    private long floorSum(long n, long a, long b, long c) {
        long ans = 0;
        
        // Simplify 'a' and 'b' if they are greater than or equal to 'c'
        if (a >= c) {
            ans += (n * (n + 1) / 2) * (a / c);
            a %= c;
        }
        if (b >= c) {
            ans += (n + 1) * (b / c);
            b %= c;
        }
        
        // Calculate the maximum value of the floor function
        long y_max = (a * n + b) / c;
        if (y_max == 0) {
            return ans;
        }
        
        // Recursive Euclidean step
        ans += y_max * n - floorSum(y_max - 1, c, c - b - 1, a);
        return ans;
    }
}