class Solution {
    // Arrays ko bahar rakho taaki baar-baar allocate na ho
    int[] s1 = new int[100001]; 
    int[] s2 = new int[100001];
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int max = 0;
        int timer = 0; // Har naye test case ke liye reset (Optional)
        for (int i = 0; i < n; i++) {
            // "new" karne ke bajaye bas timer badhao
            timer++; 
            int ev = 0;
            int od = 0;

            // Optimization: Agar bacha hua rasta max se chota h toh skip
            if (max >= n - i) break; 

            for (int j = i; j < n; j++) {
                int val = nums[j];
                if (val % 2 == 0) {
                    // Agar s1[val] timer ke barabar nahi h, matlab is loop me naya h
                    if (s1[val] != timer) {
                        s1[val] = timer;
                        ev++;
                    }
                } else {
                    if (s2[val] != timer) {
                        s2[val] = timer;
                        od++;
                    }
                }

                if (ev == od) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }
}