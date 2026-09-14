import java.util.*;

class Utils {
    public static int log2(long n) {
        return 63 - Long.numberOfLeadingZeros(n);
    }

    // smallest rotated array a[l...r] (inclusive) that starts from any index of a[l...r]
    // Tested by LC3886
    public static int[] smallestRotation(int[] a, int l, int r) {
        int n = r - l + 1;
        if (n == 1) return new int[]{a[l]};

        int i = 0, j = 1, k = 0;
        while (i < n && j < n && k < n) {
            int x = i + k, y = j + k;
            if (x >= n) x -= n;
            if (y >= n) y -= n;

            int v1 = a[l + x], v2 = a[l + y];
            if (v1 == v2) {
                k++;
            } else {
                if (v1 > v2) i += k + 1;
                else j += k + 1;
                if (i == j) j++;
                k = 0;
            }
        }
        int start = l + Math.min(i, j);
        int[] ans = new int[n];
        for (i = 0, j = start; i < n; i++, j++) {
            if (j > r) j = l;
            ans[i] = a[j];
        }
        return ans;
    }

    /*
    d[0] for even, d[1] for odd
    nums = "aaabaaaba" odd d[1] return [1, 2, 1, 4, 1, 2, 2], even d[0] return [0, 1, 1, 0, 0, 1, 1, 0, 0]
    d[0][i] means longest right part if middle index is i (right part) for even size palindrome
    d[1][i] means longest right part if middle index is i (right part) for odd size palindrome
    */
    public static int[][] manacher(char[] s) {
        int n = s.length;
        int[] dOdd = new int[n];
        for (int i = 0, l = 0, r = -1; i < n; ++i) {
            int k = (i > r) ? 1 : Math.min(dOdd[l + r - i], r - i + 1);
            while (0 <= i - k && i + k < n && s[i - k] == s[i + k]) k++;
            dOdd[i] = k;
            if (i + k - 1 > r) {
                l = i - k + 1;
                r = i + k - 1;
            }
        }

        int[] dEven = new int[n];
        for (int i = 0, l = 0, r = -1; i < n; i++) {
            int k = (i > r) ? 0 : Math.min(dEven[l + r - i + 1], r - i + 1);
            while (0 <= i - k - 1 && i + k < n && s[i - k - 1] == s[i + k]) {
                k++;
            }
            dEven[i] = k;
            if (i + k - 1 > r) {
                l = i - k;
                r = i + k - 1;
            }
        }
        return new int[][]{dEven, dOdd};
    }

    public static int[][] manacher(String s) {
        return manacher(s.toCharArray());
    }

    // generate primes that is <= n
    public static int[] genPrimes(int n) {
        if (n <= 1) return new int[0];
        boolean[] isComp = new boolean[n + 1];
        int len = 0;
        for (int i = 2; i <= n; i++) {
            if (!isComp[i]) {
                len++;
                for (int j = i + i; j <= n; j += i) {
                    isComp[j] = true;
                }
            }
        }
        int[] ans = new int[len];
        for (int i = 2, j = 0; i <= n; i++) {
            if (!isComp[i]) {
                ans[j++] = i;
            }
        }
        return ans;
    }

    // Return the distinct prime factors
    // NOTE: factors[0] and factors[1] are int[0]
    public static int[][] genPrimeFactors(int n) {
        int[][] factors = new int[n + 1][];
        boolean[] isComp = new boolean[n + 1];
        int[] len = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (isComp[i]) continue;
            len[i]++;
            for (int j = i + i; j <= n; j += i) {
                isComp[j] = true;
                len[j]++;
            }
        }
        for (int i = 0; i <= n; i++) {
            factors[i] = new int[len[i]];
            len[i] = 0;
        }
        for (int i = 2; i <= n; i++) {
            if (isComp[i]) continue;
            for (int j = i; j <= n; j += i) {
                factors[j][len[j]++] = i;
            }
        }
        return factors;
    }

    // generate factors (not prime factors), factors[0] is int[0]
    public static int[][] genFactors(int n) {
        int[][] factors = new int[n + 1][];
        int[] len = new int[n + 1];
        factors[0] = new int[0];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) len[j]++;
        }
        for (int i = 1; i <= n; i++) {
            factors[i] = new int[len[i]];
            len[i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                factors[j][len[j]++] = i;
            }
        }
        return factors;
    }

    public static int lowerBound(int[] a, int target) {
        return lowerBound(a, target, a.length);
    }

    public static int lowerBound(int[] a, int target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >>> 1);
            if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int upperBound(int[] a, int target) {
        return upperBound(a, target, a.length);
    }

    public static int upperBound(int[] a, int target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int lowerBound(long[] a, long target) {
        return lowerBound(a, target, a.length);
    }

    public static int lowerBound(long[] a, long target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int upperBound(long[] a, long target) {
        return upperBound(a, target, a.length);
    }

    public static int upperBound(long[] a, long target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int lowerBound(T[] a, T target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int upperBound(T[] a, T target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid].compareTo(target) <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int lowerBound(List<T> a, T target) {
        int low = 0;
        int high = a.size();
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a.get(mid).compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int upperBound(List<T> a, T target) {
        int low = 0;
        int high = a.size();
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a.get(mid).compareTo(target) <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static long or(int x, int y) {
        return ((long) x << 32) | ((long) y << 32 >>> 32);
    }

    // k = 0...nums.length - 1
    // After calling this function, nums[k] is the k-th number.
    public static int kthNumber(int[] nums, int k) {
        return kthNumberHelper(nums, k, 0, nums.length - 1);
    }

    // end is inclusive (0 to nums.length - 1)
    private static int kthNumberHelper(int[] nums, int K, int start, int end) {
        if (start == end) return nums[start];
        int i = partition(nums, start, end);
        return K <= i ? kthNumberHelper(nums, K, start, i) : kthNumberHelper(nums, K, i + 1, end);
    }

    // end is inclusive (0 to nums.length - 1)
    public static int partition(int[] nums, int start, int end) {
        int pos = start + ((end - start) >> 1);
        int pivot = nums[pos];

        int i = start, j = end;
        nums[pos] = nums[end];
        while (i < j) {
            while (i < j && nums[i] < pivot) i++;
            if (i < j) nums[j--] = nums[i];

            while (i < j && pivot < nums[j]) j--;
            if (i < j) nums[i++] = nums[j];
        }

        // pos is the final position for pivot.
        nums[i] = pivot;
        return i;
    }

    public static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    // partition nums into 3 parts [smaller than pivot, equal to pivot, larger than pivot]
    public static void partition3(int[] nums, int pivot) {
        int n = nums.length;
        int l = 0, r = n - 1;
        for (int i = 0; i <= r; i++) {
            if (nums[i] < pivot) {
                swap(nums, l++, i);
            } else if (nums[i] > pivot) {
                swap(nums, r--, i--);
            }
        }
    }

    // end is inclusive
    public static void reverse(int[] nums, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

    // Return false if next permutation is not available. (nums is not changed for this case)
    public static boolean nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return false;
        int i = n - 1;
        while (i - 1 >= 0 && nums[i - 1] >= nums[i]) {
            i--;
        }
        if (i <= 0) return false;
        for (int j = n - 1; j >= i; j--) {
            if (nums[j] > nums[i - 1]) {
                int t = nums[j];
                nums[j] = nums[i - 1];
                nums[i - 1] = t;

                reverse(nums, i, n - 1);
                break;
            }
        }
        return true;
    }

    // Return false if previous permutation is not available. (nums is not changed for this case)
    public static boolean prevPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return false;
        int i = n - 1;
        while (i - 1 >= 0 && nums[i - 1] <= nums[i]) {
            i--;
        }
        if (i <= 0) return false;
        for (int j = n - 1; j >= i; j--) {
            if (nums[j] < nums[i - 1]) {
                int t = nums[j];
                nums[j] = nums[i - 1];
                nums[i - 1] = t;

                reverse(nums, i, n - 1);
                break;
            }
        }
        return true;
    }


    // a, b could be negative
    // remainder >= 0
    // return [a / b, a % b]
    public static int[] divMod(int a, int b) {
        int r = a % b;
        int c = a / b;
        if (r < 0) {
            r += Math.abs(b);
            c++;
        }
        return new int[]{c, r};
    }

    public static List<Integer> negativeBase(int n, int base) {
        List<Integer> digits = new ArrayList<>();
        if (n == 0) {
            digits.add(0);
            return digits;
        }
        while (n != 0) {
            // t[0] = n / base
            // t[1] = n % base;
            int[] t = divMod(n, base);
            digits.add(t[1]);
            n = t[0];
        }
        Collections.reverse(digits);
        return digits;
    }

    // (a ^ b) % MOD
    public static long powMod(long a, long b, long MOD) {
        long res = 1L;
        a %= MOD; // In case a * a is overflow
        while (b > 0) {
            if ((b & 1) != 0) res = (res * a) % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    public static long pow(long a, long b) {
        long res = 1L;
        while (b > 0) {
            if ((b & 1) != 0) res = (res * a);
            a = a * a;
            b >>= 1;
        }
        return res;
    }

    public static long[][] genCombination(int n) {
        long[][] C = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i && j <= n; j++) {
                if (j == 0 || i == j) C[i][j] = 1;
                else C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]);
            }
        }
        return C;
    }

    public static long[][] genPermutation(int n) {
        long[][] P = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i && j <= n; j++) {
                if (i == 0 || j == 0) P[i][j] = 1;
                else P[i][j] = P[i][j - 1] * (i - j + 1);
            }
        }
        return P;
    }

    public static long gcd(long x, long y) {
        return x != 0 ? gcd(y % x, x) : y;
    }

    public static long lcm(long x, long y) {
        return x / gcd(x, y) * y;
    }

    public static int[] preprocessLog2(int n) { // from log2(0) .... log2(n) inclusive
        int[] lg = new int[n + 1];
        for (int k = 0, i = 1; i <= n; lg[i++] = k - 1) {
            while ((1 << k) <= i) {
                k++;
            }
        }
        return lg;
    }

    public static long max(long[] nums) {
        long ret = Long.MIN_VALUE;
        for (long v : nums) ret = Math.max(ret, v);
        return ret;
    }

    public static int max(int[] nums) {
        int ret = Integer.MIN_VALUE;
        for (int v : nums) ret = Math.max(ret, v);
        return ret;
    }

    public static long min(long[] nums) {
        long ret = Long.MAX_VALUE;
        for (long v : nums) ret = Math.min(ret, v);
        return ret;
    }

    public static int min(int[] nums) {
        int ret = Integer.MAX_VALUE;
        for (int v : nums) ret = Math.min(ret, v);
        return ret;
    }

    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }

    public static int min(int a, int b) {
        return (a <= b) ? a : b;
    }

    public static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static int max(int a, int b, int c, int d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    public static int min(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

    public static int max(int a, int b, int c, int d, int e) {
        return Math.max(Math.max(Math.max(a, b), Math.max(c, d)), e);
    }

    public static int min(int a, int b, int c, int d, int e) {
        return Math.min(Math.min(Math.min(a, b), Math.min(c, d)), e);
    }

    public static long max(long a, long b) {
        return (a >= b) ? a : b;
    }

    public static long min(long a, long b) {
        return (a <= b) ? a : b;
    }

    public static long max(long a, long b, long c) {
        return Math.max(a, Math.max(b, c));
    }

    public static long min(long a, long b, long c) {
        return Math.min(a, Math.min(b, c));
    }

    public static long max(long a, long b, long c, long d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    public static long min(long a, long b, long c, long d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

    public static long max(long a, long b, long c, long d, long e) {
        return Math.max(Math.max(Math.max(a, b), Math.max(c, d)), e);
    }

    public static long min(long a, long b, long c, long d, long e) {
        return Math.min(Math.min(Math.min(a, b), Math.min(c, d)), e);
    }
}

class Combination {
    long[] factorial;
    long[] inverseFactorial;
    long MOD;

    // O(maxSize) for preprocessing
    // NOTE: MOD must be prime, and m, n < MOD
    public Combination(int maxSize, long MOD) {
        factorial = new long[maxSize + 1];
        inverseFactorial = new long[maxSize + 1];
        factorial[0] = 1;
        inverseFactorial[0] = 1;
        this.MOD = MOD;
        for (int i = 1; i <= maxSize; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
            inverseFactorial[i] = inverse(factorial[i]);
        }
    }

    // x, y are long[1]
    // ax + by = gcd(a, b)
    private static long extGcd(long a, long b, long[] x, long[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        } else {
            long r = extGcd(b, a % b, y, x);
            y[0] -= x[0] * (a / b);
            return r;
        }
    }

    // (a * x) % MOD = 1
    // x is the inverse element
    public long inverse(long a) {
        return Utils.powMod(a, MOD - 2, MOD);
        //        long[] x = new long[1], y = new long[1];
        //        extGcd(a, MOD, x, y);
        //        return (x[0] % MOD + MOD) % MOD;
    }

    public static long inverse(long a, long MOD) {
        return Utils.powMod(a, MOD - 2, MOD);
        //        long[] x = new long[1], y = new long[1];
        //        extGcd(a, MOD, x, y);
        //        return (x[0] % MOD + MOD) % MOD;
    }

    // m >= n
    // O(1)
    public long P(int m, int n) {
        if (m < 0 || n < 0 || m < n) return 0;
        else if (m == 0 || n == 0) return 1;
        else if (m == n) return factorial[m];
            //return factorial[m] * inverse(factorial[m - n]) % MOD;
        else return factorial[m] * inverseFactorial[m - n] % MOD;
    }

    // m >= n
    // O(1)
    public long C(int m, int n) {
        if (m < 0 || n < 0 || m < n) return 0;
        else if (m == 0 || n == 0 || m == n) return 1;
            //return factorial[m] * inverse(factorial[m - n]) % MOD * inverse(factorial[n]) % MOD;
        else return factorial[m] * inverseFactorial[m - n] % MOD * inverseFactorial[n] % MOD;
    }
}

class CombinationNoMod {

    long[][] C;
    long[][] P;

    public CombinationNoMod(int maxSize) {
        C = new long[maxSize + 1][maxSize + 1];
        P = new long[maxSize + 1][maxSize + 1];
        int m = C.length;
        int n = C[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i && j < n; j++) {
                if (j == 0 || i == j) C[i][j] = 1;
                else C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i && j < n; j++) {
                if (i == 0 || j == 0) P[i][j] = 1;
                else P[i][j] = P[i][j - 1] * (i - j + 1);
            }
        }
    }

    // m >= n. O(1)
    public long P(int m, int n) {
        if (m < 0 || n < 0 || m < n) return 0;
        else if (m == 0 || n == 0) return 1;
        return P[m][n];
    }

    // m >= n. O(1)
    public long C(int m, int n) {
        if (m < 0 || n < 0 || m < n) return 0;
        else if (m == 0 || n == 0 || m == n) return 1;
        return C[m][n];
    }
}

class CombinationNoPreprocess {
    public static long C(int m, int n) {
        if (n > m - n) {
            n = m - n; // Use symmetry: C(m, n) == C(m, m - n)
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * (m - i + 1) / i;
        }
        return result;
    }

    public static long P(int m, int n) {
        long result = 1;
        for (int i = m; i > m - n; i--) {
            result = result * i;
        }
        return result;
    }
}

class UnionFind {
    int[] parent;
    int[] sz;

    // from 0 ... n - 1
    public UnionFind(int n) {
        parent = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    public void clear() {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int x) {
        //return parent[x] == x ? x : (parent[x] = find(parent[x]));
        if (parent[x] == x) return x;
        int px = x;
        while (px != parent[px]) px = parent[px];
        while (x != px) {
            int next = parent[x];
            parent[x] = px;
            x = next;
        }
        return px;
    }

    // px is the final parent
    public boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return false;
        parent[py] = px;
        sz[px] += sz[py];
        return true;
    }

    public int size(int x) {
        return sz[find(x)];
    }

    public UnionFind clone() {
        UnionFind cloned = new UnionFind(parent.length);
        for (int i = 0; i < parent.length; i++) {
            cloned.parent[i] = parent[i];
            cloned.sz[i] = sz[i];
        }
        return cloned;
    }
}

// Tested by Leetcode 307
class DynamicSegmentTreeRangeSum {
    class TreeNode {
        TreeNode left, right;
        long sum;
    }

    private final TreeNode root;
    private final long L, R;

    public DynamicSegmentTreeRangeSum(long l, long r) {
        L = l;
        R = r;
        root = new TreeNode();
    }

    public long get(long index) {
        long l = L, r = R;
        TreeNode cur = root;
        while (l < r && cur != null) {
            long m = l + ((r - l) >> 1);
            if (index <= m) {
                cur = cur.left;
                r = m;
            } else {
                cur = cur.right;
                l = m + 1;
            }
        }
        return sum(cur);
    }

    public void add(long index, long value) {
        set(index, get(index) + value);
    }

    public void set(long index, long value) {
        set(root, L, R, index, value);
    }

    // Sum[queryL...queryR] inclusive
    public long query(long queryL, long queryR) {
        return query(root, L, R, queryL, queryR);
    }

    private long sum(TreeNode node) {
        return node == null ? 0L : node.sum;
    }

    private void set(TreeNode root, long L, long R, long index, long value) {
        if (index < L || R < index) return;
        if (L == R) {
            root.sum = value;
            return;
        }
        long M = L + ((R - L) >> 1);
        if (index <= M) {
            if (root.left == null) root.left = new TreeNode();
            set(root.left, L, M, index, value);
        } else {
            if (root.right == null) root.right = new TreeNode();
            set(root.right, M + 1, R, index, value);
        }
        root.sum = sum(root.left) + sum(root.right);
    }

    // [queryL, queryR] is range of query, [L, R] are range of TreeNode.
    private long query(TreeNode root, long L, long R, long queryL, long queryR) {
        if (root == null || queryL > R || queryR < L) return 0L;
        if (queryL <= L && R <= queryR) return root.sum;
        long M = L + ((R - L) >> 1);
        return query(root.left, L, M, queryL, queryR) + query(root.right, M + 1, R, queryL, queryR);
    }
}

// Tested by Leetcode 239, 2926
class DynamicSegmentTreeRangeMax {
    class TreeNode {
        TreeNode left, right;
        long max = Long.MIN_VALUE;
    }

    private final TreeNode root;
    private final long L, R;

    public DynamicSegmentTreeRangeMax(long l, long r) {  // [l, r] inclusive
        L = l;
        R = r;
        root = new TreeNode();
    }

    public long get(long index) {
        long l = L, r = R;
        TreeNode cur = root;
        while (l < r && cur != null) {
            long m = l + ((r - l) >> 1);
            if (index <= m) {
                cur = cur.left;
                r = m;
            } else {
                cur = cur.right;
                l = m + 1;
            }
        }
        return max(cur);
    }

    private long max(TreeNode node) {
        return node == null ? Long.MIN_VALUE : node.max;
    }

    public void set(long index, long value) {
        set(root, L, R, index, value);
    }

    private void set(TreeNode root, long L, long R, long index, long value) {
        if (index < L || R < index) return;
        if (L == R) {
            root.max = value;
            return;
        }

        long M = L + ((R - L) >> 1);
        if (index <= M) {
            if (root.left == null) root.left = new TreeNode();
            set(root.left, L, M, index, value);
        } else {
            if (root.right == null) root.right = new TreeNode();
            set(root.right, M + 1, R, index, value);
        }
        root.max = Math.max(max(root.left), max(root.right));
    }

    // Max[queryL...queryR] inclusive
    public long query(long queryL, long queryR) {
        return query(root, L, R, queryL, queryR);
    }

    // [queryL, queryR] is range of query, [L, R] are range of TreeNode.
    private long query(TreeNode root, long L, long R, long queryL, long queryR) {
        if (root == null || queryL > R || queryR < L) return Long.MIN_VALUE;
        if (queryL <= L && R <= queryR) return root.max;
        long M = L + ((R - L) >> 1);
        return Math.max(query(root.left, L, M, queryL, queryR), query(root.right, M + 1, R, queryL, queryR));
    }
}

class DynamicSegmentTreeRangeMin {
    class TreeNode {
        TreeNode left, right;
        long min = Long.MAX_VALUE;
    }

    private final TreeNode root;
    private final long L, R;

    public DynamicSegmentTreeRangeMin(long l, long r) {  // [l, r] inclusive
        L = l;
        R = r;
        root = new TreeNode();
    }

    public long get(long index) {
        long l = L, r = R;
        TreeNode cur = root;
        while (l < r && cur != null) {
            long m = l + ((r - l) >> 1);
            if (index <= m) {
                cur = cur.left;
                r = m;
            } else {
                cur = cur.right;
                l = m + 1;
            }
        }
        return min(cur);
    }

    private long min(TreeNode node) {
        return node == null ? Long.MAX_VALUE : node.min;
    }

    public void set(long index, long value) {
        set(root, L, R, index, value);
    }

    private void set(TreeNode root, long L, long R, long index, long value) {
        if (index < L || R < index) return;
        if (L == R) {
            root.min = value;
            return;
        }

        long M = L + ((R - L) >> 1);
        if (index <= M) {
            if (root.left == null) root.left = new TreeNode();
            set(root.left, L, M, index, value);
        } else {
            if (root.right == null) root.right = new TreeNode();
            set(root.right, M + 1, R, index, value);
        }
        root.min = Math.min(min(root.left), min(root.right));
    }

    // Max[queryL...queryR] inclusive
    public long query(long queryL, long queryR) {
        return query(root, L, R, queryL, queryR);
    }

    // [queryL, queryR] is range of query, [L, R] are range of TreeNode.
    private long query(TreeNode root, long L, long R, long queryL, long queryR) {
        if (root == null || queryL > R || queryR < L) return Long.MAX_VALUE;
        if (queryL <= L && R <= queryR) return root.min;
        long M = L + ((R - L) >> 1);
        return Math.min(query(root.left, L, M, queryL, queryR), query(root.right, M + 1, R, queryL, queryR));
    }
}

class BinaryIndexTree {
    long[] c;
    private int n;

    // NOTE: index is from 1 to n. (NOT 0 ... n - 1)
    public BinaryIndexTree(int n) {
        this.n = n;
        this.c = new long[n + 1];
    }

    // Add value to position k, k is from 1 to n.
    public void add(int k, long value) {
        while (k <= n) {
            c[k] += value;
            k += (k & (k ^ (k - 1)));
        }
    }

    // k is from 1 to n.
    public long getSum(int k) {
        long sum = 0;
        while (k > 0) {
            sum += c[k];
            k -= (k & (k ^ (k - 1)));
        }
        return sum;
    }
}

class RangeSum {
    BinaryIndexTree tree;
    int start;

    // [low, high] inclusive
    public RangeSum(int low, int high) {
        start = low;
        tree = new BinaryIndexTree(high - low + 1);
    }

    public RangeSum(int[] input) {
        this(0, input.length - 1);
        for (int i = 0; i < input.length; i++) set(i, input[i]);
    }

    public void clear() {
        Arrays.fill(tree.c, 0);
    }

    public void add(int index, long value) {
        tree.add(index - start + 1, value);
    }

    public long get(int index) {
        return tree.getSum(index - start + 1) - tree.getSum(index - start);
    }

    public void set(int index, long value) {
        tree.add(index - start + 1, value - get(index));
    }

    // [l, r] inclusive
    public long getSum(int l, int r) {
        return tree.getSum(r - start + 1) - tree.getSum(l - start);
    }
}

class RangeMax {
    int start;
    int n;
    long[] t;

    // [low, high] inclusive
    public RangeMax(int low, int high) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, Long.MIN_VALUE);
    }

    public RangeMax(int[] input) {
        this(0, input.length - 1);
        for (int i = 0; i < input.length; i++) set(i, input[i]);
    }

    public RangeMax(int low, int high, long defaultValue) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, defaultValue);
    }

    long get(int index) {
        return t[index - start + n];
    }

    void set(int index, long value) {
        index -= start;
        for (t[index += n] = value; (index >>= 1) > 0; ) {
            t[index] = Math.max(t[index << 1], t[index << 1 | 1]);
        }
    }

    // [l, r] inclusive
    long getMax(int l, int r) {
        l -= start;
        r = (r + 1 - start); // make it as [l, r)
        long resl = Long.MIN_VALUE, resr = Long.MIN_VALUE;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) resl = Math.max(resl, t[l++]);
            if ((r & 1) != 0) resr = Math.max(t[--r], resr);
        }
        return Math.max(resl, resr);
    }
}

class RangeMin {
    int start;
    int n;
    long[] t;

    // [low, high] inclusive
    public RangeMin(int low, int high) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, Long.MAX_VALUE);
    }

    public RangeMin(int[] input) {
        this(0, input.length - 1);
        for (int i = 0; i < input.length; i++) set(i, input[i]);
    }

    public RangeMin(int low, int high, long defaultValue) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, defaultValue);
    }

    long get(int index) {
        return t[index - start + n];
    }

    void set(int index, long value) {
        index -= start;
        for (t[index += n] = value; (index >>= 1) > 0; ) {
            t[index] = Math.min(t[index << 1], t[index << 1 | 1]);
        }
    }

    // [l, r] inclusive
    long getMin(int l, int r) {
        l -= start;
        r = (r + 1 - start); // make it as [l, r)
        long resl = Long.MAX_VALUE, resr = Long.MAX_VALUE;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) resl = Math.min(resl, t[l++]);
            if ((r & 1) != 0) resr = Math.min(t[--r], resr);
        }
        return Math.min(resl, resr);
    }
}

// Including topological sort.
class GraphIntegerFaster {
    public List<Integer>[] adj;
    int n;

    public GraphIntegerFaster(int n) {
        this.n = n;
        this.adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
    }

    public void clear() {
        for (int i = 0; i < n; i++) adj[i].clear();
    }

    // directed edge x -> y
    public void link(int x, int y) {
        adj[x].add(y);
    }

    public List<Integer> getChildren(int x) {
        return adj[x];
    }

    private static final int TEMPORARY = 1;
    private static final int PERMANENT = 2;

    // x -> y means x should be processed before y
    // The input is a directed graph.
    // 1. return null, means there is a cycle inside the graph, or the input is
    // invalid.
    // 2. return List<Node> as the result.
    public List<Integer> topologicalSort() {
        int n = adj.length;
        int[] vst = new int[n];
        List<Integer> result = new ArrayList<>();
        for (int x = 0; x < n; x++) {
            if (!topologicalSort(x, vst, result)) {
                return null;
            }
        }
        Collections.reverse(result);
        return result;
    }

    private boolean topologicalSort(int root, int[] vst, List<Integer> result) {
        if (vst[root] == PERMANENT) {
            return true;
        }
        if (vst[root] == TEMPORARY) { // found cycle
            return false;
        }

        vst[root] = TEMPORARY;
        for (int child : getChildren(root)) {
            if (!topologicalSort(child, vst, result)) {
                return false;
            }
        }
        result.add(root);
        vst[root] = PERMANENT;
        return true;
    }
}

class DijkstraLongPQFast {
    static final long INF = Long.MAX_VALUE / 4;

    int n, cnt;
    int[] head, to, next;
    long[] w;

    public DijkstraLongPQFast(int n) {
        this.n = n;
        head = new int[n];
        Arrays.fill(head, -1);

        int cap = Math.max(4, n);
        to = new int[cap];
        next = new int[cap];
        w = new long[cap];
    }

    public void clear() {
        cnt = 0;
        Arrays.fill(head, -1);
    }

    public void link(int x, int y, long weight) {
        if (cnt == to.length) {
            int cap = to.length << 1;
            to = Arrays.copyOf(to, cap);
            next = Arrays.copyOf(next, cap);
            w = Arrays.copyOf(w, cap);
        }
        to[cnt] = y;
        w[cnt] = weight;
        next[cnt] = head[x];
        head[x] = cnt++;
    }

    public long[] shortestPath(int source) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.add(new long[]{0, source});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int x = (int) cur[1];
            if (d != dist[x]) continue;

            for (int e = head[x]; e != -1; e = next[e]) {
                int y = to[e];
                long nd = d + w[e];
                if (nd < dist[y]) {
                    dist[y] = nd;
                    pq.add(new long[]{nd, y});
                }
            }
        }
        return dist;
    }

    // Shortest path when every edge has the same weight.
    public long[] bfs(int source) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        int[] q = new int[n];
        int l = 0, r = 0;
        long sameWeight = (cnt == 0) ? 1 : w[0];
        q[r++] = source;

        while (l < r) {
            int x = q[l++];
            long nd = dist[x] + sameWeight;
            for (int e = head[x]; e != -1; e = next[e]) {
                int y = to[e];
                if (dist[y] == INF) {
                    dist[y] = nd;
                    q[r++] = y;
                }
            }
        }
        return dist;
    }

    public long[] shortestPathFaster(int source) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        MinHeap pq = new MinHeap(Math.max(4, cnt + 1));
        pq.add(0, source);
        while (!pq.isEmpty()) {
            long d = pq.peekDist();
            int x = pq.peekNode();
            pq.poll();
            if (d != dist[x]) continue;

            for (int e = head[x]; e != -1; e = next[e]) {
                int y = to[e];
                long nd = d + w[e];

                if (nd < dist[y]) {
                    dist[y] = nd;
                    pq.add(nd, y);
                }
            }
        }
        return dist;
    }

    // PriorityQueue-like MinHeap for pair: (distance, node)
    class MinHeap {
        int size;
        long[] dist;
        int[] node;

        MinHeap(int cap) {
            dist = new long[cap];
            node = new int[cap];
        }

        boolean isEmpty() {
            return size == 0;
        }

        long peekDist() {
            return dist[0];
        }

        int peekNode() {
            return node[0];
        }

        void add(long d, int x) {
            if (size == dist.length) {
                dist = Arrays.copyOf(dist, size * 2);
                node = Arrays.copyOf(node, size * 2);
            }
            int i = size++;
            while (i > 0) {
                int p = (i - 1) >> 1;
                if (dist[p] <= d) break;
                dist[i] = dist[p];
                node[i] = node[p];
                i = p;
            }
            dist[i] = d;
            node[i] = x;
        }

        void poll() {
            long d = dist[--size];
            int x = node[size];
            int i = 0;
            while ((i << 1) + 1 < size) {
                int l = (i << 1) + 1;
                int c = (l + 1) < size && dist[l + 1] < dist[l] ? l + 1 : l;
                if (dist[c] >= d) break;
                dist[i] = dist[c];
                node[i] = node[c];
                i = c;
            }
            if (size > 0) {
                dist[i] = d;
                node[i] = x;
            }
        }
    }
}

class TreapSet<E> {
    private class Node {
        E key;
        int priority, count, total;
        Node left, right, pnt;

        public Node(E key, int priority, Node pnt) {
            this.key = key;
            this.priority = priority;
            this.pnt = pnt;
            this.count = 1;
            this.total = 1;
        }
    }

    private static final Random RANDOM = new Random();
    private final Comparator<? super E> comparator;
    private Node root;

    public TreapSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public int size() {
        return root == null ? 0 : root.total;
    }

    private int leftRank(Node p) {
        return p.left != null ? p.left.total : 0;
    }

    private int rightRank(Node p) {
        return p.right != null ? p.right.total : 0;
    }

    private void rotateLeft(Node x) {
        Node y = x.right;
        if ((x.right = y.left) != null) y.left.pnt = x;
        y.pnt = x.pnt;
        if (x == root) root = y;
        else if (x == x.pnt.left) x.pnt.left = y;
        else x.pnt.right = y;
        y.left = x;
        x.pnt = y;
        x.total = leftRank(x) + rightRank(x) + x.count;
        y.total = leftRank(y) + rightRank(y) + y.count;
    }

    private void rotateRight(Node x) {
        Node y = x.left;
        if ((x.left = y.right) != null) y.right.pnt = x;
        y.pnt = x.pnt;
        if (x == root) root = y;
        else if (x == x.pnt.right) x.pnt.right = y;
        else x.pnt.left = y;
        y.right = x;
        x.pnt = y;
        x.total = leftRank(x) + rightRank(x) + x.count;
        y.total = leftRank(y) + rightRank(y) + y.count;
    }

    public void add(E key) {
        if (root == null) {
            root = new Node(key, RANDOM.nextInt(), null);
            return;
        }
        Node x = root, p = null;
        while (x != null) {
            ++((p = x).total);
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                ++(x.count);
                return;
            }
        }

        x = new Node(key, RANDOM.nextInt(), p);
        if (comparator.compare(key, p.key) < 0) p.left = x;
        else p.right = x;
        while ((p = x.pnt) != null && p.priority < x.priority) {
            if (p.left == x) rotateRight(p);
            else rotateLeft(p);
        }
    }

    public boolean remove(E key) {
        Node x = root, p = null;
        while (x != null) {
            --(x.total);
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                if (--(x.count) > 0) return true;
                break;
            }
        }
        if (x == null) { // key is not found, restore total count of the nodes
            for (x = root; x != null; ) {
                ++(x.total);
                x = (comparator.compare(key, x.key) < 0) ? x.left : x.right;
            }
            return false;
        }
        while (x.left != null || x.right != null) {
            if (x.left == null || (x.right != null && x.right.priority > x.left.priority)) {
                rotateLeft(x);
            } else {
                rotateRight(x);
            }
        }
        if ((p = x.pnt) != null) {
            if (p.left == x) p.left = null;
            else p.right = null;
        } else {
            root = null;
        }
        return true;
    }

    // number of elements smaller than key
    public int lowerCount(E key) {
        int sum = 0;
        Node x = root;
        while (x != null) {
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) {
                sum += leftRank(x) + x.count;
                x = x.right;
            } else {
                sum += leftRank(x);
                break;
            }
        }
        return sum;
    }

    // number of elements larger than key
    public int higherCount(E key) {
        //return size() - lowerCount(key) - count(key);
        int sum = 0;
        Node x = root;
        while (x != null) {
            int cmp = comparator.compare(key, x.key);
            if (cmp > 0) x = x.right;
            else if (cmp < 0) {
                sum += rightRank(x) + x.count;
                x = x.left;
            } else {
                sum += rightRank(x);
                break;
            }
        }
        return sum;
    }

    public int index(E key) {
        return lowerCount(key);
    }

    public E get(int index) // index = 0 ... size - 1
    {
        Node p = root;
        ++index;
        while (true) {
            int t = leftRank(p);
            if (index <= t) p = p.left;
            else {
                if ((index -= t + p.count) <= 0) break;
                p = p.right;
            }
        }
        return p.key;
    }

    public int count(E key) {
        Node x = root;
        while (x != null) {
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) {
                x = x.right;
            } else {
                return x.count;
            }
        }
        return 0;
    }

    public E first() {
        if (root == null) return null;
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    public E last() {
        if (root == null) return null;
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    public boolean contains(E key) {
        return count(key) > 0;
    }

    public E ceiling(E key) {
        int id = lowerCount(key);
        return id >= size() ? null : get(id);
    }

    public E floor(E key) {
        int id = size() - higherCount(key) - 1;
        return id < 0 ? null : get(id);
    }

    public E lower(E key) {
        int id = lowerCount(key) - 1;
        return id < 0 ? null : get(id);
    }

    public E higher(E key) {
        int id = size() - higherCount(key);
        return id >= size() ? null : get(id);
    }

    // return the first index whose value >= target
    // if this value doesn't exist, return index = size()
    public int lowerBound(E target) {
        return lowerCount(target);
    }

    // return the first index whose value > target
    // if this value doesn't exist, return index = size()
    public int upperBound(E target) {
        return size() - higherCount(target);
    }

    public List<E> uniqueKeys() {
        List<E> list = new ArrayList<>();
        inorder(root, list, true);
        return list;
    }

    public List<E> allKeys() {
        List<E> list = new ArrayList<>();
        inorder(root, list, false);
        return list;
    }

    private void inorder(Node x, List<E> list, boolean unique) {
        if (x == null) return;
        inorder(x.left, list, unique);
        if (unique) {
            list.add(x.key);
        } else {
            for (int i = 0; i < x.count; i++) {
                list.add(x.key);
            }
        }
        inorder(x.right, list, unique);
    }
}

class ArraySum {
    long[] sum;
    int n;

    // nums index starts from 0 to nums.length - 1;
    public ArraySum(int[] nums) {
        n = nums.length;
        sum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public ArraySum(long[] nums) {
        n = nums.length;
        sum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public ArraySum(final List<? extends Number> nums) {
        n = nums.size();
        sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums.get(i - 1).longValue();
        }
    }

    // [l, r], index starts from 0
    public long getSum(int l, int r) {
        // error handling
        if (l > r || !(l >= 0 && l < n && r >= 0 && r < n)) return 0L;
        return sum[r + 1] - sum[l];
    }
}

class ArrayUtils {
    static void fill(int[] array, int defaultValue) {
        Arrays.fill(array, defaultValue);
    }

    static void fill(int[][] array, int defaultValue) {
        for (int[] a1 : array) {
            Arrays.fill(a1, defaultValue);
        }
    }

    static void fill(int[][][] array, int defaultValue) {
        for (int[][] a2 : array) {
            for (int[] a1 : a2) {
                Arrays.fill(a1, defaultValue);
            }
        }
    }

    static void fill(int[][][][] array, int defaultValue) {
        for (int[][][] a3 : array) {
            for (int[][] a2 : a3) {
                for (int[] a1 : a2) {
                    Arrays.fill(a1, defaultValue);
                }
            }
        }
    }

    static void fill(int[][][][][] array, int defaultValue) {
        for (int[][][][] a4 : array) {
            for (int[][][] a3 : a4) {
                for (int[][] a2 : a3) {
                    for (int[] a1 : a2) {
                        Arrays.fill(a1, defaultValue);
                    }
                }
            }
        }
    }

    static void fill(int[][][][][][] array, int defaultValue) {
        for (int[][][][][] a5 : array) {
            for (int[][][][] a4 : a5) {
                for (int[][][] a3 : a4) {
                    for (int[][] a2 : a3) {
                        for (int[] a1 : a2) {
                            Arrays.fill(a1, defaultValue);
                        }
                    }
                }
            }
        }
    }

    static void fill(long[] array, long defaultValue) {
        Arrays.fill(array, defaultValue);
    }

    static void fill(long[][] array, long defaultValue) {
        for (long[] a1 : array) {
            Arrays.fill(a1, defaultValue);
        }
    }

    static void fill(long[][][] array, long defaultValue) {
        for (long[][] a2 : array) {
            for (long[] a1 : a2) {
                Arrays.fill(a1, defaultValue);
            }
        }
    }

    static void fill(long[][][][] array, long defaultValue) {
        for (long[][][] a3 : array) {
            for (long[][] a2 : a3) {
                for (long[] a1 : a2) {
                    Arrays.fill(a1, defaultValue);
                }
            }
        }
    }

    static void fill(long[][][][][] array, long defaultValue) {
        for (long[][][][] a4 : array) {
            for (long[][][] a3 : a4) {
                for (long[][] a2 : a3) {
                    for (long[] a1 : a2) {
                        Arrays.fill(a1, defaultValue);
                    }
                }
            }
        }
    }

    static void fill(long[][][][][][] array, long defaultValue) {
        for (long[][][][][] a5 : array) {
            for (long[][][][] a4 : a5) {
                for (long[][][] a3 : a4) {
                    for (long[][] a2 : a3) {
                        for (long[] a1 : a2) {
                            Arrays.fill(a1, defaultValue);
                        }
                    }
                }
            }
        }
    }

    static void copy(long[] src, long[] dest) {
        System.arraycopy(src, 0, dest, 0, src.length);
    }

    static void copy(long[][] src, long[][] dest) {
        for (int i = 0; i < src.length; i++) {
            copy(src[i], dest[i]);
        }
    }

    static void copy(long[][][] src, long[][][] dest) {
        for (int i = 0; i < src.length; i++) {
            copy(src[i], dest[i]);
        }
    }

    static void copy(long[][][][] src, long[][][][] dest) {
        for (int i = 0; i < src.length; i++) {
            copy(src[i], dest[i]);
        }
    }

    static void copy(long[][][][][] src, long[][][][][] dest) {
        for (int i = 0; i < src.length; i++) {
            copy(src[i], dest[i]);
        }
    }

    static void copy(long[][][][][][] src, long[][][][][][] dest) {
        for (int i = 0; i < src.length; i++) {
            copy(src[i], dest[i]);
        }
    }

    static void copy(int[] src, int[] dest) {
        System.arraycopy(src, 0, dest, 0, src.length);
    }

    static void copy(int[][] src, int[][] dest) {
        for (int i = 0; i < src.length; i++) {
            copy(src[i], dest[i]);
        }
    }

    static void copy(int[][][] src, int[][][] dest) {
        for (int i = 0; i < src.length; i++) {
            copy(src[i], dest[i]);
        }
    }

    static void copy(int[][][][] src, int[][][][] dest) {
        for (int i = 0; i < src.length; i++) {
            copy(src[i], dest[i]);
        }
    }

    static void copy(int[][][][][] src, int[][][][][] dest) {
        for (int i = 0; i < src.length; i++) {
            copy(src[i], dest[i]);
        }
    }

    static void copy(int[][][][][][] src, int[][][][][][] dest) {
        for (int i = 0; i < src.length; i++) {
            copy(src[i], dest[i]);
        }
    }

    static void print(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    static void print(int[][] a) {
        System.out.println("\n----------");
        for (int i = 0; i < a.length; i++) print(a[i]);
        System.out.println("----------\n");
    }

    static void print(long[] a) {
        System.out.println(Arrays.toString(a));
    }

    static void print(long[][] a) {
        System.out.println("\n----------");
        for (int i = 0; i < a.length; i++) print(a[i]);
        System.out.println("----------\n");
    }

    @FunctionalInterface
    public interface IntComparator {
        int compare(int a, int b); // <0: a<b, 0: a==b, >0: a>b
    }

    public static void sort(int[] nums, IntComparator cmp) {
        sort(nums, 0, nums.length - 1, cmp);
    }

    private static final Random RAND = new Random(0);

    // [l, r]
    public static void sort(int[] a, int l, int r, IntComparator cmp) {
        if (l >= r) return;
        int i = l, j = r;
        int m = l + RAND.nextInt(r - l + 1); //l + ((r - l) >>> 1);
        int pivot = a[m]; // median(a[l], a[m], a[r], cmp);

        while (i <= j) {
            while (cmp.compare(a[i], pivot) < 0) i++;
            while (cmp.compare(a[j], pivot) > 0) j--;
            if (i <= j) {
                int tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        sort(a, l, j, cmp);
        sort(a, i, r, cmp);
    }

    private static int median(int a, int b, int c, IntComparator cmp) {
        return cmp.compare(a, b) < 0 ?
                (cmp.compare(b, c) < 0 ? b : (cmp.compare(a, c) < 0 ? c : a)) :
                (cmp.compare(a, c) < 0 ? a : (cmp.compare(b, c) < 0 ? c : b));
    }
}

class CharacterUtils {
    static boolean[] vowel = new boolean[128];

    static {
        vowel['a'] = vowel['e'] = vowel['i'] = vowel['o'] = vowel['u'] = true;
        vowel['A'] = vowel['E'] = vowel['I'] = vowel['O'] = vowel['U'] = true;
    }

    static boolean isVowel(char ch) {
        return vowel[ch];
    }

    static boolean isLetter(char ch) {
        return ((ch - 'a') >= 0 && (ch - 'a') < 26) ||
                ((ch - 'A') >= 0 && (ch - 'A') < 26);
    }

    static boolean isLowerCase(char ch) {
        return (ch - 'a') >= 0 && (ch - 'a') < 26;
    }

    static boolean isUpperCase(char ch) {
        return (ch - 'A') >= 0 && (ch - 'A') < 26;
    }

    static boolean isDigit(char ch) {
        return (ch - '0') >= 0 && (ch - '0') <= 9;
    }
}

class StringUtils {
    // smallest rotated string that starts from any index of s.
    // Tested by LC899
    public static String smallestRotation(String s) {
        return new String(smallestRotation(s.toCharArray()));
    }

    public static char[] smallestRotation(char[] a) {
        if (a.length <= 1) return a;
        int n = a.length;
        int i = 0, j = 1, k = 0;
        while (i < n && j < n && k < n) {
            int x = i + k, y = j + k;
            if (x >= n) x -= n;
            if (y >= n) y -= n;

            char c1 = a[x], c2 = a[y];
            if (c1 == c2) {
                k++;
            } else {
                if (c1 > c2) i += k + 1;
                else j += k + 1;
                if (i == j) j++;
                k = 0;
            }
        }

        int start = Math.min(i, j);
        char[] res = new char[n];
        int len = 0;
        for (i = start; i < n; i++) res[len++] = a[i];
        for (i = 0; i < start; i++) res[len++] = a[i];
        return res;
    }

    // This function is deprecated, please hash(int l, int r) in class StableStringHash or StringHash
    // rolling hash of substrings of 's' with length == k
    // long[pos] = hash(s.substring(pos, pos + k))
    public static long[] rollingHash(String s, int k) {
        long[] ans = new long[s.length() - k + 1];
        int seed1 = 31;
        int seed2 = 131;
        int h1 = 0, h2 = 0, power1 = 1, power2 = 1;
        for (int i = 0; i < k; i++) {
            h1 = h1 * seed1 + s.charAt(i);
            h2 = h2 * seed2 + s.charAt(i);

            power1 *= seed1;
            power2 *= seed2;
        }

        ans[0] = ((long) h1 << 32) | ((long) h2 << 32 >>> 32);
        for (int i = k; i < s.length(); i++) {
            h1 = h1 * seed1 + s.charAt(i) - power1 * s.charAt(i - k);
            h2 = h2 * seed2 + s.charAt(i) - power2 * s.charAt(i - k);
            ans[i - k + 1] = ((long) h1 << 32) | ((long) h2 << 32 >>> 32);
        }

        return ans;
    }

    // For example: split by '*', "**" will return {"", "", ""}
    // This function is equal to s.split("\\*", -1) for char '*'
    public static String[] split(String s, char ch) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ch) count++;
        }
        String[] ans = new String[count + 1];
        int i = 0;
        int len = 0;
        for (; i < s.length(); ) {
            int j = i;
            while (j < s.length() && s.charAt(j) != ch) j++;
            ans[len++] = s.substring(i, j);
            i = j + 1;
        }
        if (len < ans.length) ans[len++] = "";
        return ans;
    }

    public static int[] kmpNext(String s) {
        int n = s.length();
        int[] next = new int[n];
        next[0] = 0;
        for (int i = 1; i < n; i++) {
            int k = next[i - 1];
            while (k > 0 && s.charAt(i) != s.charAt(k)) k = next[k - 1];
            if (s.charAt(i) == s.charAt(k)) {
                next[i] = k + 1;
            } else {
                next[i] = 0;
            }
        }
        return next;
    }

    // s is the original String
    // p is the pattern String
    public static boolean kmpMatch(String s, String p) {
        int[] next = kmpNext(p);
        int n = s.length();
        int m = p.length();
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && p.charAt(j) != s.charAt(i)) {
                j = next[j - 1];
            }
            if (p.charAt(j) == s.charAt(i)) j++;
            if (j == m) return true;
        }
        return false;
    }

    // return all match index
    // if there is no match, return an empty list (not null list)
    public static List<Integer> kmpMatchAll(String s, String p) {
        int[] next = kmpNext(p);
        int n = s.length();
        int m = p.length();
        List<Integer> pos = new ArrayList<>();
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && p.charAt(j) != s.charAt(i)) {
                j = next[j - 1];
            }
            if (p.charAt(j) == s.charAt(i)) j++;
            if (j == m) {
                pos.add(i - m + 1);
                j = next[j - 1];
            }
        }
        return pos;
    }

    public static int[] kmpNext(int[] s) {
        int n = s.length;
        int[] next = new int[n];
        next[0] = 0;
        for (int i = 1; i < n; i++) {
            int k = next[i - 1];
            while (k > 0 && s[i] != s[k]) k = next[k - 1];
            if (s[i] == s[k]) {
                next[i] = k + 1;
            } else {
                next[i] = 0;
            }
        }
        return next;
    }

    // s is the original String
    // p is the pattern String
    public static boolean kmpMatch(int[] s, int[] p) {
        int[] next = kmpNext(p);
        int n = s.length;
        int m = p.length;
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && p[j] != s[i]) {
                j = next[j - 1];
            }
            if (p[j] == s[i]) j++;
            if (j == m) return true;
        }
        return false;
    }

    // Z[i] = longest length of common prefix of str[0 ... n - 1] and str[i ... n - 1]
    // Tested by LC2223, LC459
    public static int[] zFunction(String str) {
        int n = str.length();
        int[] Z = new int[n];
        // [L,R] makes a window which matches with prefix of s
        int L = 0, R = 0;
        Z[0] = n;
        for (int i = 1; i < n; ++i) {
            if (i > R) {
                L = R = i;
                while (R < n && str.charAt(R - L) == str.charAt(R)) R++;
                Z[i] = R - L;
                R--;
            } else {
                int k = i - L;
                if (Z[k] < R - i + 1) {
                    Z[i] = Z[k];
                } else {
                    L = i;
                    while (R < n && str.charAt(R - L) == str.charAt(R)) R++;
                    Z[i] = R - L;
                    R--;
                }
            }
        }
        return Z;
    }

    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}

class SingleStringHash {
    final long[] h;
    final long[] pow;
    final long SEED;
    final long MOD;

    public SingleStringHash(String s) {
        this(s, 131L, 1_000_000_007L); // Other seeds 1319L
    }

    public SingleStringHash(String s, long SEED, long MOD) { // example: SEED = 131L;
        this.SEED = SEED;
        this.MOD = MOD;
        int n = s.length();
        h = new long[n + 1];
        pow = new long[n + 1];
        pow[0] = 1;
        for (int i = 1; i <= n; i++) pow[i] = (pow[i - 1] * SEED) % MOD;
        //h[i] = hash[s[0...i - 1]]
        h[0] = 0;
        for (int i = 1; i <= n; i++) {
            h[i] = (h[i - 1] * SEED + s.charAt(i - 1)) % MOD;
        }
    }

    // hash[s[l....r]]
    public long hash(int l, int r) {
        long ret = (h[r + 1] - h[l] * pow[r - l + 1]) % MOD;
        return ret >= 0 ? ret : ret + MOD;
    }
}

// Better to use this when length of string >= 2^11
class StringHash {
    static final long SEED1 = 31L, SEED2 = 131L; // Other seeds 1319L
    static final long MOD = 1_000_000_007L;
    SingleStringHash hash1, hash2;

    public StringHash(String s) {
        hash1 = new SingleStringHash(s, SEED1, MOD);
        hash2 = new SingleStringHash(s, SEED2, MOD);
    }

    public long hash(int l, int r) {
        return (hash1.hash(l, r) << 32) | (hash2.hash(l, r) << 32 >>> 32);
    }

    public static long hashCode(String s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.length(); i++) {
            h1 = (h1 * SEED1 + s.charAt(i)) % MOD;
            h2 = (h2 * SEED2 + s.charAt(i)) % MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }

    public static long hashCode(char[] s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.length; i++) {
            h1 = (h1 * SEED1 + s[i]) % MOD;
            h2 = (h2 * SEED2 + s[i]) % MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }
}

class IntegerArrayHash { // Tested by LC1923
    static class SingleIntegerArrayHash {
        long[] h;
        long[] pow;
        final long SEED;
        final long MOD;

        public SingleIntegerArrayHash(int[] s, long SEED, long MOD) { // example: SEED = 131L;
            this.SEED = SEED;
            this.MOD = MOD;
            int n = s.length;
            h = new long[n + 1];
            pow = new long[n + 1];
            pow[0] = 1;
            for (int i = 1; i <= n; i++) pow[i] = (pow[i - 1] * SEED) % MOD;
            //h[i] = hash[s[0...i - 1]]
            h[0] = 0;
            for (int i = 1; i <= n; i++) {
                h[i] = (h[i - 1] * SEED + s[i - 1]) % MOD;
                if (h[i] < 0) h[i] += MOD;
            }
        }

        // hash[s[l....r]]
        public long hash(int l, int r) {
            long ret = (h[r + 1] - h[l] * pow[r - l + 1]) % MOD;
            return ret >= 0 ? ret : ret + MOD;
        }
    }

    private SingleIntegerArrayHash hash1, hash2;
    //static final long SEED1 = 31L, SEED2 = 131L; // these SEEDs are too small, not fit for int or long values
    static final long SEED1 = 911_382_323L;
    static final long SEED2 = 972_663_749L;
    static final long MOD = 1_000_000_007L; // or 1_000_000_123L

    public IntegerArrayHash(int[] s) {
        hash1 = new SingleIntegerArrayHash(s, SEED1, MOD);
        hash2 = new SingleIntegerArrayHash(s, SEED2, MOD);
    }

    public long hash(int l, int r) {
        return (hash1.hash(l, r) << 32) | (hash2.hash(l, r) << 32 >>> 32);
    }

    public static long hashCode(int... s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.length; i++) {
            h1 = (h1 * SEED1 + s[i]) % MOD;
            if (h1 < 0) h1 += MOD;
            h2 = (h2 * SEED2 + s[i]) % MOD;
            if (h2 < 0) h2 += MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }

    public static long hashCode(List<Integer> s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.size(); i++) {
            h1 = (h1 * SEED1 + s.get(i)) % MOD;
            if (h1 < 0) h1 += MOD;
            h2 = (h2 * SEED2 + s.get(i)) % MOD;
            if (h2 < 0) h2 += MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }
}

class IntArray {
    public final int[] a;

    public IntArray(int numOfElement) {
        a = new int[numOfElement];
    }

    public IntArray(int... v) {
        a = v;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof IntArray) {
            return o == this || (Arrays.equals(a, ((IntArray) o).a));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(a); // Can cache hash value to improve performance
    }


    @Override
    public String toString() {
        return Arrays.toString(a);
    }
}

class LongArray {
    public final long[] a;

    public LongArray(int numOfElement) {
        a = new long[numOfElement];
    }

    public LongArray(long... v) {
        a = v;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LongArray) {
            return o == this || (Arrays.equals(a, ((LongArray) o).a));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(a); // Can cache hash value to improve performance
    }

    @Override
    public String toString() {
        return Arrays.toString(a);
    }
}

class HashUtils {
    static final long SEED1 = 31L, SEED2 = 131L;
    static final long MOD = 1_000_000_007L; // or 1_000_000_123L

    public static long hash(String s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.length(); i++) {
            h1 = (h1 * SEED1 + s.charAt(i)) % MOD;
            h2 = (h2 * SEED2 + s.charAt(i)) % MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }

    // Tested by LC3714
    public static long hash(int... a) {
        long z = (long) a.length * 0x9E3779B97F4A7C15L;
        for (int x : a) {
            z += (x & 0xFFFF_FFFFL) + 0x9E3779B97F4A7C15L;
            z = (z ^ (z >>> 30)) * 0xBF58476D1CE4E5B9L;
            z = (z ^ (z >>> 27)) * 0x94D049BB133111EBL;
            z ^= (z >>> 31);
        }
        return z;
    }

    public static long hash(long... a) {
        long z = (long) a.length * 0x9E3779B97F4A7C15L;
        for (long x : a) {
            z += x + 0x9E3779B97F4A7C15L;
            z = (z ^ (z >>> 30)) * 0xBF58476D1CE4E5B9L;
            z = (z ^ (z >>> 27)) * 0x94D049BB133111EBL;
            z ^= (z >>> 31);
        }
        return z;
    }
}

class LongCompressor {
    long[] vals;
    int m, n;
    boolean dirty = true;

    public LongCompressor() {
        vals = new long[16];
    }

    public LongCompressor(long[] values) {
        vals = values.clone();
        m = values.length;
    }

    public LongCompressor(int[] values) {
        vals = new long[values.length];
        for (int i = 0; i < values.length; i++) vals[i] = values[i];
        m = values.length;
    }

    public void add(long value) {
        if (m == vals.length) vals = Arrays.copyOf(vals, m * 2 + 1);
        vals[m++] = value;
        dirty = true;
    }

    // return compressed index of value, 0-based; -1 if missing
    public int getIndex(long value) {
        build();
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >>> 1);
            if (vals[mid] < value) l = mid + 1;
            else if (vals[mid] > value) r = mid - 1;
            else return mid;
        }
        return -1;
    }

    public long getValue(int index) {
        build();
        return vals[index];
    }

    public int size() {
        build();
        return n;
    }

    private void build() {
        if (!dirty) return;
        Arrays.sort(vals, 0, m);
        n = 0;
        for (int i = 0; i < m; i++) {
            if (n == 0 || vals[n - 1] != vals[i]) vals[n++] = vals[i];
        }
        dirty = false;
    }

    public static int compressValues(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        for (int x : sorted) {
            if (m == 0 || sorted[m - 1] != x) sorted[m++] = x;
        }
        for (int i = 0; i < n; i++) {
            nums[i] = Utils.lowerBound(sorted, nums[i], m);
        }
        return m;
    }
}

class Printer {
    static boolean ENABLE_LOCAL_PRINT = false;

    static void println(String x) {
        if (!ENABLE_LOCAL_PRINT) return; // do nothing
        System.out.println(x);
    }

    static void printf(String format, Object... args) {
        if (!ENABLE_LOCAL_PRINT) return; // do nothing
        System.out.printf(format, args);
    }
}


public class Solution {
    private static final int INF = 1_000_000_001;
    //private static final long INF = 1L << 50;
    private static final long MOD = 1_000_000_007L;

    private static final int UNSET = -131;
    // 4 neighbors
    private static final int[] DX = {-1, 0, 1, 0}; // up, right, down, left
    private static final int[] DY = {0, 1, 0, -1};

    // 8 neighbors
    //    private static final int[] DX = {-1, -1, 0, 1, 1,  1, 0,  -1};
    //    private static final int[] DY = {0,   1, 1, 1, 0, -1, -1, -1};

    // knight
//    private static final int[] DX = {-2, -2, -1, 1, 2, 2, 1, -1};
//    private static final int[] DY = {-1, 1, 2, 2, 1, -1, -2, -2};

    static class SuffixArray {
        int n;
        int[] s;
        int[] SA; // SA[i] => index of the i-th ranked suffix
        int[] rank; // rank[i] => the rank of i-th suffix, SA[rank[i]] == i
        int[] height; // height[i] = LCP(s[SA[i]], s[SA[i - 1]]).

        private int[][] d;
        private int[] lg;

        SuffixArray(String str) {
            this(str, true);
        }

        SuffixArray(String str, boolean toBuildRMQ) {
            n = str.length();
            this.s = new int[n];
            for (int i = 0; i < n; i++) s[i] = str.charAt(i);
            SA = sais(s, 255);
            if (toBuildRMQ) buildRMQ();
        }

        SuffixArray(int[] nums) {
            this(nums, true);
        }

        SuffixArray(int[] nums, boolean toBuildRMQ) {
            n = nums.length;
            int min = nums[0], max = nums[0];
            for (int v : nums) {
                if (min > v) min = v;
                if (max < v) max = v;
            }
            this.s = new int[n];
            for (int i = 0; i < n; i++) s[i] = nums[i] - min;
            int upper = max - min;
            if (upper + 1 > n) upper = LongCompressor.compressValues(s) - 1;
            SA = sais(s, upper);
            if (toBuildRMQ) buildRMQ();
        }

        private int log2(int n) {
            return 31 - Integer.numberOfLeadingZeros(n);
        }

        // height[i] = LCP(s[SA[i]], s[SA[i - 1]])
        private void buildHeight() {
            int i, j, h;
            height[0] = 0;
            for (i = 0; i < n; i++) rank[SA[i]] = i;
            for (h = i = 0; i < n; i++)
                if (rank[i] > 0) {
                    j = SA[rank[i] - 1];
                    while (i + h < n && j + h < n && s[i + h] == s[j + h]) ++h;
                    height[rank[i]] = h;
                    if (h > 0) --h;
                }
        }

        // LCP(i, j) = LCP(s[SA[i]], s[SA[j]]) = min{height[k] | i + 1 <= k <= j}.
        private void buildRMQ() {
            this.height = new int[n];
            this.rank = new int[n];
            this.d = new int[log2(n) + 1][n];
            this.lg = new int[n + 1];
            buildHeight();

            int i, j, k;
            for (i = 0; i < n; i++)
                d[0][i] = height[i];
            for (j = 1; (1 << j) <= n; j++)
                for (i = 0; i + (1 << j) <= n; i++)
                    d[j][i] = Math.min(d[j - 1][i], d[j - 1][i + (1 << (j - 1))]);
            for (lg[0] = k = 0, i = 1; i <= n; lg[i++] = k - 1)
                while ((1 << k) <= i) k++;
        }

        // LCP(i, j) = LCP(s[SA[i]], s[SA[j]]) = min{height[k] | i + 1 <= k <= j}.
        public int LCP(int i, int j) {
            if (i == j) return n - SA[i];
            if (i > j) {
                int t = i;
                i = j;
                j = t;
            }
            int k = lg[j - (++i) + 1];
            return Math.min(d[k][i], d[k][j - (1 << k) + 1]);
        }

        // LCPIndex(i, j) = LCP(s[i], s[j])
        public int LCPIndex(int i, int j) {
            return LCP(rank[i], rank[j]);
        }

        // Requires 0 <= s[i] <= upper.
        static int[] sais(int[] s, int upper) {
            int n = s.length;
            if (n == 0) return new int[0];
            if (n == 1) return new int[]{0};
            if (n == 2)
                return s[0] < s[1] ? new int[]{0, 1} : new int[]{1, 0};

            int[] sa = new int[n];
            boolean[] ls = new boolean[n];

            for (int i = n - 2; i >= 0; i--)
                ls[i] = s[i] == s[i + 1] ? ls[i + 1] : s[i] < s[i + 1];

            int[] sumL = new int[upper + 2];
            int[] sumS = new int[upper + 2];

            for (int i = 0; i < n; i++)
                if (ls[i]) sumL[s[i] + 1]++;
                else sumS[s[i]]++;

            for (int i = 0; i <= upper; i++) {
                sumS[i] += sumL[i];
                sumL[i + 1] += sumS[i];
            }

            int[] map = new int[n + 1];
            Arrays.fill(map, -1);

            int m = 0;
            for (int i = 1; i < n; i++)
                if (!ls[i - 1] && ls[i]) map[i] = m++;

            int[] lms = new int[m];
            for (int i = 1, p = 0; i < n; i++)
                if (!ls[i - 1] && ls[i]) lms[p++] = i;

            induce(s, ls, sumL, sumS, lms, sa);

            if (m > 0) {
                int[] sorted = new int[m];

                for (int i = 0, p = 0; i < n; i++)
                    if (map[sa[i]] != -1) sorted[p++] = sa[i];

                int[] rec = new int[m];
                int recUpper = 0;
                rec[map[sorted[0]]] = 0;

                for (int i = 1; i < m; i++) {
                    int l = sorted[i - 1], r = sorted[i];

                    int el = map[l] + 1 < m ? lms[map[l] + 1] : n;
                    int er = map[r] + 1 < m ? lms[map[r] + 1] : n;

                    boolean same = el - l == er - r;

                    if (same) {
                        while (l < el && s[l] == s[r]) {
                            l++;
                            r++;
                        }
                        if (l == n || s[l] != s[r]) same = false;
                    }

                    if (!same) recUpper++;
                    rec[map[sorted[i]]] = recUpper;
                }

                int[] recSa = sais(rec, recUpper);

                for (int i = 0; i < m; i++)
                    sorted[i] = lms[recSa[i]];

                induce(s, ls, sumL, sumS, sorted, sa);
            }
            return sa;
        }

        static void induce(int[] s, boolean[] ls, int[] sumL,
                           int[] sumS, int[] lms, int[] sa) {
            int n = s.length;
            Arrays.fill(sa, -1);

            int[] buf = sumS.clone();
            for (int x : lms) sa[buf[s[x]]++] = x;

            buf = sumL.clone();
            sa[buf[s[n - 1]]++] = n - 1;

            for (int i = 0; i < n; i++) {
                int x = sa[i];
                if (x >= 1 && !ls[x - 1])
                    sa[buf[s[x - 1]]++] = x - 1;
            }

            buf = sumL.clone();

            for (int i = n - 1; i >= 0; i--) {
                int x = sa[i];
                if (x >= 1 && ls[x - 1])
                    sa[--buf[s[x - 1] + 1]] = x - 1;
            }
        }
    }
    
    public int countDistinct(String s) {
        int n = s.length();
        int total = n * (n + 1) / 2;
        SuffixArray sa = new SuffixArray(s);
        for (int i = 1; i < n; i++) {
            total -= sa.LCP(i - 1, i);
        }
        return total;
    }



    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Printer.ENABLE_LOCAL_PRINT = true;
        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */






        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */
        System.out.println((System.currentTimeMillis() - startTime) + "ms");
    }
}