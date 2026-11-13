class Solution {
    public long minCuttingCost(int n, int m, int k) {
        

        if(n <= k && m <= k) return 0;

        else {
            long cost = 0;
            if(n > k) {
                long diff = n-k;
                cost = k*diff;
            }
            else if(m > k) {
                long diff = m-k;
                cost = diff*k;
            }

            return cost;
        }
    }
}