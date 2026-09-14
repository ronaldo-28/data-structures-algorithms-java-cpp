class Solution {
    public int minLights(int[] lights) {
        int n = lights.length;
        int[] b = new int[n];

        int reach = -1;

        for (int i = 0; i < n; i++) {
            if (lights[i] != 0) {
                reach = Math.max(reach, i + lights[i]);
            }
            if (reach >= i) {
                b[i] = 1;
            }
        }

        reach = n + 1;

        for (int i = n - 1; i >= 0; i--) {
            if (lights[i] != 0) {
                reach = Math.min(reach, i - lights[i]);
            }
            if (reach <= i) {
                b[i] = 1;
            }
        }

        int ans = 0, cnt = 0;

        for (int i = 0; i < n; i++) {
            if (b[i] == 1) {
                if (cnt != 0) {
                    ans += (cnt - 1) / 3 + 1;
                }
                cnt = 0;
            } else {
                cnt++;
            }
        }

        if (cnt != 0) {
            ans += (cnt - 1) / 3 + 1;
        }

        return ans;
    }
}