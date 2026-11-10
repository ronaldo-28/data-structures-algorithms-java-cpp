class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long n = nums.length;
        
        if (k == 1) {
            ans[0] = n * (n + 1) / 2;
            return ans;
        }
        
        if (k == 2) {
            long dp0 = 0, dp1 = 0;
            for (int x : nums) {
                if (x % 2 == 0) {
                    dp0 = 1 + dp0 + dp1;
                    dp1 = 0;
                } else {
                    dp1 = 1 + dp1;
                }
                ans[0] += dp0;
                ans[1] += dp1;
            }
            return ans;
        }
        
        if (k == 3) {
            long dp0 = 0, dp1 = 0, dp2 = 0;
            for (int x : nums) {
                int val = x % 3;
                long n0 = 0, n1 = 0, n2 = 0;
                if (val == 0) {
                    n0 = 1 + dp0 + dp1 + dp2;
                } else if (val == 1) {
                    n0 = dp0; 
                    n1 = 1 + dp1; 
                    n2 = dp2;
                } else {
                    n0 = dp0; 
                    n1 = dp2; 
                    n2 = 1 + dp1;
                }
                dp0 = n0; 
                dp1 = n1; 
                dp2 = n2;
                ans[0] += dp0; 
                ans[1] += dp1; 
                ans[2] += dp2;
            }
            return ans;
        }
        
        if (k == 4) {
            long dp0 = 0, dp1 = 0, dp2 = 0, dp3 = 0;
            for (int x : nums) {
                int val = x % 4;
                long n0 = 0, n1 = 0, n2 = 0, n3 = 0;
                if (val == 0) {
                    n0 = 1 + dp0 + dp1 + dp2 + dp3;
                } else if (val == 1) {
                    n0 = dp0; 
                    n1 = 1 + dp1; 
                    n2 = dp2; 
                    n3 = dp3;
                } else if (val == 2) {
                    n0 = dp0 + dp2; 
                    n1 = 0; 
                    n2 = 1 + dp1 + dp3; 
                    n3 = 0;
                } else {
                    n0 = dp0; 
                    n1 = dp3; 
                    n2 = dp2; 
                    n3 = 1 + dp1;
                }
                dp0 = n0; 
                dp1 = n1; 
                dp2 = n2; 
                dp3 = n3;
                ans[0] += dp0; 
                ans[1] += dp1; 
                ans[2] += dp2; 
                ans[3] += dp3;
            }
            return ans;
        }
        
        if (k == 5) {
            long dp0 = 0, dp1 = 0, dp2 = 0, dp3 = 0, dp4 = 0;
            for (int x : nums) {
                int val = x % 5;
                long n0 = 0, n1 = 0, n2 = 0, n3 = 0, n4 = 0;
                if (val == 0) {
                    n0 = 1 + dp0 + dp1 + dp2 + dp3 + dp4;
                } else if (val == 1) {
                    n0 = dp0; 
                    n1 = 1 + dp1; 
                    n2 = dp2; 
                    n3 = dp3; 
                    n4 = dp4;
                } else if (val == 2) {
                    n0 = dp0; 
                    n1 = dp3; 
                    n2 = 1 + dp1; 
                    n3 = dp4; 
                    n4 = dp2;
                } else if (val == 3) {
                    n0 = dp0; 
                    n1 = dp2; 
                    n2 = dp4; 
                    n3 = 1 + dp1; 
                    n4 = dp3;
                } else {
                    n0 = dp0; 
                    n1 = dp4; 
                    n2 = dp3; 
                    n3 = dp2; 
                    n4 = 1 + dp1;
                }
                dp0 = n0; 
                dp1 = n1; 
                dp2 = n2; 
                dp3 = n3; 
                dp4 = n4;
                ans[0] += dp0; 
                ans[1] += dp1; 
                ans[2] += dp2; 
                ans[3] += dp3; 
                ans[4] += dp4;
            }
            return ans;
        }
        
        return ans;
    }
}