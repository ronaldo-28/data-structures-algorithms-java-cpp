class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int max_len = 1;
        int curr = 1;
        int prev = arr[0];
        boolean up = true;

        for (int i=1;i<n;i++) {
            curr++;
            if (arr[i] > prev) {
                if (up) {
                    curr = 2;
                } else {
                    up = true;
                }
            } else if (arr[i] < prev) {
                if (up) {
                    up = false;
                } else {
                    curr = 2;
                }
            } else curr = 1;
            prev = arr[i];
            max_len = Math.max(max_len, curr);
        }

        return max_len;
    }
}