class Solution {
    public long[] countStableSubarrays(int[] nums, int[][] queries) {
        long[] count = new long[nums.length];
        int[] ss = new int[nums.length];
        int[] ee = new int[nums.length];
        count[0] = 1;
        ss[0] = 0;
        int start = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) {
                count[i] = count[i - 1] + i - start + 1;
            } else {
                ee[start] = i - 1;
                start = i;
                count[i] = count[i - 1] + 1;
            }
            ss[i] = start;
        }
        ee[start] = nums.length - 1;

        //System.out.println(Arrays.toString(count));
        //System.out.println(Arrays.toString(ss));
        //System.out.println(Arrays.toString(ee));

        long[] ans = new long[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int f = queries[qi][0];
            int t = queries[qi][1];
            if (f == 0) {
                ans[qi] = count[t];
            } else {
                ans[qi] = count[t] - count[f - 1];
                if (f > ss[f]) {
                    int pref = f - ss[f];
                    if (t > ee[ss[f]]) {
                        ans[qi] -= pref * (ee[ss[f]] - f + 1);
                    } else {
                        ans[qi] -= pref * (t - f + 1);
                    }
                }
            }
        }
        return ans;
    }
}