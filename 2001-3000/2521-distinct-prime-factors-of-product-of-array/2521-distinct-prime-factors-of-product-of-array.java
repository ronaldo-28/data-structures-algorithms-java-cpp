class Solution {
    public int distinctPrimeFactors(int[] nums) {
        int n = nums.length;
        int[] spf = buildSPF(1000);
        boolean[] set = new boolean[1001];
        set[1] = true;
        int unique = 0;
        for (int num : nums) {
            while (spf[num] != 0) {
                int p = spf[num];
                while (num % p == 0) num /= p;
                if (!set[p]) unique++;
                set[p] = true;
            }
            
            if (!set[num]) unique++;
            set[num] = true;
        }
        
        return unique;
    }

    private int[] buildSPF(int max) {
        int[] spf = new int[max + 1];

        for (int i = 2; i * i <= max; i++) {
            if (spf[i] == 0) {
                for (int j = i * i; j <= max; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }

        return spf;
    }
}