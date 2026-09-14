enum State { INIT, WIP, DONE }

class Solution {
    static {
        for (int i = 0 ; i < 500 ; i++) {
            maximumInvitations(new int[]{0 , 1});
        }
    }

    public static int maximumInvitations(int[] nums) {
        int n = nums.length;
        int[] ins = new int[n];
        int[] q = new int[n];
        int deep[] = new int[n];

        for (int i = 0 ; i < n ; i++) {
            ins[nums[i]]++;
        }
        int l = 0;
        int r = 0;

        for (int i = 0 ; i < n ; i++) {
            if(ins[i] == 0) q[r++] = i;
        }
        while (l < r) {
            int cur = q[l++];
            int next = nums[cur];
            deep[next] = Math.max(deep[next] , deep[cur] + 1);
            if(--ins[next] == 0) q[r++] = next;
        }
        int small = 0;
        int big = 0;

        for (int i = 0 ; i < n ; i++) {
            if (ins[i] != 0) {
                ins[i] = 0;
                int count = 1;

                for (int j = nums[i] ; j != i ; j = nums[j]) {
                    count++;
                    ins[j] = 0;
                }
                if (count == 2) {
                    small += deep[i] + deep[nums[i]] + 2;
                } else {
                    big = Math.max(big , count);
                }
            }
        }
        return Math.max(big , small);
    }
}