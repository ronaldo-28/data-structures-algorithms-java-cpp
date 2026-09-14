class Solution {
    public int countGoodSubseq(int[] nums, int p, int[][] queries) {
        if (nums.length <= 6) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] % p == 0) {
                    nums[i] /= p;
                } else {
                    nums[i] = 0;
                }
            }
            int res = 0;
            for (int[] query : queries) {
                int idx = query[0];
                int num = query[1];
                if (num % p == 0) {
                    num /= p;
                } else {
                    num = 0;
                }
                nums[idx] = num;
                for (int i = 0; i < nums.length; i++) {
                    int gcd = 0;
                    for (int j = 0; j < nums.length; j++) {
                        if (i == j)
                            continue;
                        if (gcd == 0) {
                            gcd = nums[j];
                        } else if (nums[j] != 0) {
                            gcd = GCD(gcd, nums[j]);
                        }
                    }
                    if (gcd == 1) {
                        res++;
                        break;
                    }
                }
            }
            return res;
        } else {
            len = 1 << ((int) Math.ceil(Math.log(nums.length) / Math.log(2)));
            gcds = new int[2 * len - 1];
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] % p == 0) {
                    gcds[len - 1 + i] = nums[i] / p;
                }
            }
            for (int i = len - 2; i >= 0; i--) {
                update(i);
            }
            int res = 0;
            for (int[] query : queries) {
                query(query, p);
                if (gcds[0] == 1)
                    res++;
            }
            return res;
        }
    }

    int len;
    int[] gcds;

    public void query(int[] q, int p) {
        int idx = len - 1 + q[0];
        int num = q[1];
        if (num % p == 0) {
            num /= p;
        } else {
            num = 0;
        }
        if (gcds[idx] == num)
            return;
        gcds[idx] = num;
        while (idx > 0) {
            idx = (idx - 1) / 2;
            update(idx);
        }
    }

    public void update(int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (gcds[l] == 0) {
            gcds[i] = gcds[r];
        } else if (gcds[r] == 0) {
            gcds[i] = gcds[l];
        } else {
            gcds[i] = GCD(gcds[l], gcds[r]);
        }
    }

    public int GCD(int a, int b) {
        if (b == 0)
            return a;
        return GCD(b, a % b);
    }
}