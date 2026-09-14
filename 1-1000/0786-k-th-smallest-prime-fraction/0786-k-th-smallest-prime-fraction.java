class Solution {
    public int[] get(double mid, int[] arr, int k) {
        int l = 0;
        int r = 0;
        int n = arr.length;
        double val = 2.0;
        int i = -1;
        int j = -1;
        int cnt = 0;
        int[]ans=new int[3];
        while (r < n) {
            while (arr[l] < mid * arr[r]) {
                l++;
            } 
            // [,0.33,0.5,0.66]
            if (l != r && val >= (1.0 * arr[l] / arr[r])) {
                val = ((1.0 * arr[l]) / arr[r]);
                i = arr[l];
                j = arr[r];
            }
            cnt += (r - l);

            r++;
        }
        // System.out.println(mid+" "+cnt);
        ans[0] = i;
        ans[1] = j;
        ans[2]=cnt;
        return ans;
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        double lo = 0.0;
        double hi = 1.0;
        double eps = 1e-9;
        int[] ans = new int[2];
        int n = arr.length;
        k = (((n) * (n - 1)) / 2) - k+1;
        while (hi - lo > eps) {
            double mid = (lo + hi) / 2;
            int[] count = get(mid, arr, k);
            if (count[2] < k) {
                hi = mid;
            } else {
                if (count[2] == k) {
                    ans[0] = count[0];
                    ans[1] = count[1];
                    break;
                }

                lo = mid;
            }
        }
        return ans;
    }
}