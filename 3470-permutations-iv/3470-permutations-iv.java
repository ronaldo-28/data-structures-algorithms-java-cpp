class Solution {
     static public int[] permute(int n, long k) {
        if (n == 1) return one(n, k);
        if (n == 2) return two(n, k);
        if (n == 3) return three(n, k);
        if (n == 4) return four(n, k);

        long x = 1, pro = 2;
        long[] arr = new long[n];
        int idx = n - 4;
        arr[n - 3] = 1;
        for (int i = 1; i < 23 && i < n - 2; i += 1) {
            x = x * pro;
            arr[idx--] = x;
            if (i % 2 == 0) pro++;
        }

        ArrayList<Integer> odd = new ArrayList<>();
        ArrayList<Integer> even = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 1) odd.add(i);
            else even.add(i);
        }

        if (arr[0] > 0) {
            if (n % 2 == 0) {
                if ((odd.size() * 2) * arr[0] < k) return new int[] {};
            } else 
                if (odd.size() * arr[0] < k) return new int[] {};
            
        }
        int[] res = new int[n];
        idx = 0;
        int firstOdd = solve(n, k, arr[0]);
        if(arr[0] == 0) firstOdd = 1000;
        if (n % 2 == 1 || (firstOdd < 0 || firstOdd == 1000)) {
            for (int i = 0; i < n - 3; i++) {
                if (i % 2 == 0) {
                    if (arr[i] == 0) {
                        res[idx++] = odd.get(0);
                        odd.remove(0);
                    } else {
                        int index = index(odd, arr[i], k);
                        if(n % 2 == 0){
                            if(i == 0) index = -firstOdd;
                            if(index == -1000) index = 0;
                        }
                        res[idx++] = odd.get(index);
                        odd.remove(index);
                    }
                } else {
                    if (arr[i] == 0) {
                        res[idx++] = even.get(0);
                        even.remove(0);
                    } else {
                        int index = index(even, arr[i], k);
                        res[idx++] = even.get(index);
                        even.remove(index);
                    }
                }
            }
        } else {
            for (int i = 0; i < n - 3; i++) {
                if (i % 2 == 1) {
                    if (arr[i] == 0) {
                        res[idx++] = odd.get(0);
                        odd.remove(0);
                    } else {
                        int index = index(odd, arr[i], k);
                        res[idx++] = odd.get(index);
                        odd.remove(index);
                    }
                } else {
                    if (arr[i] == 0) {
                        res[idx++] = even.get(0);
                        even.remove(0);
                    } else {
                        int index = index(even, arr[i], k);
                        if (firstOdd >= 0) {
                            if (i == 0) index = firstOdd;
                        }
                        res[idx++] = even.get(index);
                        even.remove(index);
                    }
                }
            }
        }

        if (n % 2 == 0 && (firstOdd < 0 || firstOdd == 1000)) {
            if (k % 2 == 0) {
                res[n - 1] = even.get(0);
                res[n - 3] = even.get(1);
            } else {
                res[n - 3] = even.get(0);
                res[n - 1] = even.get(1);
            }
            res[n - 2] = odd.get(0);
        }
        if (n % 2 == 1 || (firstOdd >= 0 && firstOdd != 1000)) {
            if (k % 2 == 0) {
                res[n - 1] = odd.get(0);
                res[n - 3] = odd.get(1);
            } else {
                res[n - 3] = odd.get(0);
                res[n - 1] = odd.get(1);
            }
            res[n - 2] = even.get(0);
        }

        return res;
    }

    static int solve(int n, long k, long corresponds) {
        long temp = 0;
        int x = 0, y = 0;
        for (int i = 0; i < 2 * n; i++) {
            temp += corresponds;
            if (temp > k) {
                if (i == 2 * n - 1) {
                    return n - 1;
                } else {
                    if (i % 2 == 0){
                        if(i == 0) return 1000;
                        return -y;
                    }
                    return x;
                }
            }
            if (i % 2 == 1) x++;
            else y++;
        }
        return x;
    }

    static int[] four(int n, long k) {
        if (k > 8) return new int[] {};
        if (k == 1) return new int[] { 1, 2, 3, 4 };
        if (k == 2) return new int[] { 1, 4, 3, 2 };
        if (k == 3) return new int[] { 2, 1, 4, 3 };
        if (k == 4) return new int[] { 2, 3, 4, 1 };
        if (k == 5) return new int[] { 3, 2, 1, 4 };
        if (k == 6) return new int[] { 3, 4, 1, 2 };
        if (k == 7) return new int[] { 4, 1, 2, 3 };
        return new int[] { 4, 3, 2, 1 };
    }

    static int[] three(int n, long k) {
        if (k > 2) return new int[] {};
        if (k == 1) return new int[] { 1, 2, 3 };
        return new int[] { 3, 2, 1 };
    }

    static int[] two(int n, long k) {
        if (k > 2) return new int[] {};
        if (k == 1) return new int[] { 1, 2 };
        return new int[] { 2, 1 };
    }

    static int[] one(int n, long k) {
        if (k > 1) return new int[] {};
        return new int[] { 1 };
    }

    static int index(ArrayList<Integer> l, long corresponds, long requires) {
        int n = l.size(), idx = 0;
        long sum = corresponds;
        requires %= n * corresponds;
        if (requires == 0) return n - 1;
        for (int i = 0; i < n; i++, sum += corresponds)
            if (requires <= sum) return i;
        return -1;
    }
}