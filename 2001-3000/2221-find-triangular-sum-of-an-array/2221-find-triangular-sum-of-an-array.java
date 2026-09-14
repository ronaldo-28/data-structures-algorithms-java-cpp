import java.util.*;

class Solution {
    // Precompute C(a,b) mod 5 for a,b in [0..4]
    private static final int[][] C5 = buildC5();

    public int triangularSum(int[] nums) {
        int n = nums.length;
        int N = n - 1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int c2 = combMod2(N, i);         // 0 or 1
            int c5 = combMod5(N, i);         // 0..4
            int c10 = crt10(c2, c5);         // 0..9
            ans = (ans + c10 * nums[i]) % 10;
        }
        return ans;
    }

    // C(N,i) mod 2: odd iff no carries in i + (N - i)
    private static int combMod2(int N, int i) {
        return ((i & (N - i)) == 0) ? 1 : 0;
    }

    // Lucas mod 5
    private static int combMod5(int N, int i) {
        int res = 1;
        int n = N, k = i;
        while (n > 0 || k > 0) {
            int nd = n % 5, kd = k % 5;
            if (kd > nd) return 0;
            res = (res * C5[nd][kd]) % 5;
            n /= 5; k /= 5;
        }
        return res;
    }

    // Combine residues r ≡ r2 (mod 2), r ≡ r5 (mod 5) to r (mod 10)
    private static int crt10(int r2, int r5) {
        int r = r5;                 // r in {0,1,2,3,4}
        if ((r & 1) != r2) r += 5;  // pick r or r+5 to match parity
        return r;                   // 0..9
    }

    private static int[][] buildC5() {
        int[][] c = new int[5][5];
        for (int a = 0; a < 5; a++) {
            c[a][0] = c[a][a] = 1;
            for (int b = 1; b < a; b++) c[a][b] = (c[a-1][b-1] + c[a-1][b]) % 5;
        }
        return c;
    }

    // Reference O(n^2) check (does not modify caller input)
    static int triangularSumSlow(int[] nums) {
        int[] a = Arrays.copyOf(nums, nums.length);
        for (int len = a.length; len > 1; len--) {
            for (int j = 0; j < len - 1; j++) a[j] = (a[j] + a[j+1]) % 10;
        }
        return a[0];
    }
}