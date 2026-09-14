class Solution {
    // defining MOD to be used for final calculation 
    //defining array to store final results 
    private static final int MOD = 1000000007;
    private static long[] ans = new long[1001];

    public int sumOfBlocks(int n) {
        //valid parameter check - constraints
        if(n < 1 || n > 1000) {
            throw new IllegalArgumentException("Not a valid entry");
        } 
        else {
            if(ans[1] == 0) {// if first time running function
                int current = 1;
                for(int i = 1; i <= 1000; i++) {
                    long sum = 1;
                    for(int j = 0; j < i; j++) {
                        sum = sum * current++ % MOD; //find the product of the next i numbers
                        } 
                        ans[i] = (ans[i - 1] + sum) % MOD; //add the product to the previous answer
                }
            }
            return (int)ans[n];
        }   
    }
}