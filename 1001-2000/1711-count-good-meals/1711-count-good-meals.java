class Solution {
        public int countPairs(int[] deliciousness) {
        int MOD = 1_000_000_007;
        int MAX = 0;
        
        long count = 0;
        for(int val: deliciousness){
            if(val>MAX)MAX=val;
        }
        int[] freq = new int[MAX + 1];
        for (int val : deliciousness) {
            // for (int power = 1; power <= 1 × 2²¹ = 2_097_152; power = power * 2)
            for (int power = 1; power <= MAX*2; power <<= 1) {
                int need = power - val;
                if (need >= 0 && need <= MAX) {
                    count += freq[need];
                }
            }
            freq[val]++;
        }
        // if count = 4_999_950_000, can not return with integer value. By using % MOD, to make sure it always returns number < 1_000_000_007
        return (int) (count % MOD);
    }
    }