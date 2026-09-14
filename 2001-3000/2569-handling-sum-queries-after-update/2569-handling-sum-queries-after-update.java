import java.util.*;

public class Solution {

    public static int MAXN = 100001;

    // 原始数组（1-based）
    public static int[] arr = new int[MAXN];

    // sum[i]: 节点 i 覆盖范围内 1 的数量
    public static int[] sum = new int[MAXN << 2];

    // reverse[i]: 节点 i 是否有"待下发的取反任务"
    // 注意: 取反任务是"布尔状态", 不是"次数累计" —— 偶数次取反等于没取反
    public static boolean[] reverse = new boolean[MAXN << 2];

    // ============================================================
    // ===== up: sum 汇总 =====
    // ============================================================
    public static void up(int i) {
        // 父 sum = 左子 sum + 右子 sum
        sum[i] = sum[i << 1] + sum[i << 1 | 1];
    }

    // ============================================================
    // ===== down: 懒信息下发 =====
    // ============================================================
    public static void down(int i, int ln, int rn) {
        if (reverse[i]) {
            reverseLazy(i << 1,     ln);   // 发左
            reverseLazy(i << 1 | 1, rn);   // 发右
            reverse[i] = false;             // 父的取反标记清空
        }
    }

    // ============================================================
    // ===== reverseLazy: 在节点 i 上"懒住"一个取反任务 =====
    // ============================================================
    public static void reverseLazy(int i, int n) {
        // 取反后: 原本有 sum[i] 个 1, 现在 0 变 1、1 变 0
        // 新的 1 数量 = n - sum[i]
        sum[i] = n - sum[i];

        // 取反标记翻转 (偶数次取反 = 没取反, 所以用异或语义)
        reverse[i] = !reverse[i];
    }

    // ============================================================
    // ===== build: 建树 =====
    // ============================================================
    public static void build(int l, int r, int i) {
        if (l == r) {
            // 叶子: 单点 sum = arr[l] 本身 (0 或 1)
            sum[i] = arr[l];
        } else {
            int mid = (l + r) >> 1;
            build(l, mid, i << 1);
            build(mid + 1, r, i << 1 | 1);
            up(i);
        }
        reverse[i] = false;
    }

    // ============================================================
    // ===== reverse: 范围取反 =====
    // ============================================================
    public static void reverse(int jobl, int jobr, int l, int r, int i) {
        if (jobl <= l && r <= jobr) {
            // 任务范围完全覆盖当前范围 -> 懒住
            reverseLazy(i, r - l + 1);
        } else {
            int mid = (l + r) >> 1;
            down(i, mid - l + 1, r - mid);
            if (jobl <= mid) {
                reverse(jobl, jobr, l, mid, i << 1);
            }
            if (jobr > mid) {
                reverse(jobl, jobr, mid + 1, r, i << 1 | 1);
            }
            up(i);
        }
    }

    // ============================================================
    // ===== querySum: 范围 1 的数量 =====
    // ============================================================
    // 这道题只需要全局 sum, 所以查询时永远是 [1, n]
    // 但保留通用 query 接口
    public static int querySum(int jobl, int jobr, int l, int r, int i) {
        if (jobl <= l && r <= jobr) {
            return sum[i];
        }
        int mid = (l + r) >> 1;
        down(i, mid - l + 1, r - mid);
        int ans = 0;
        if (jobl <= mid) {
            ans += querySum(jobl, jobr, l, mid, i << 1);
        }
        if (jobr > mid) {
            ans += querySum(jobl, jobr, mid + 1, r, i << 1 | 1);
        }
        return ans;
    }

    // ============================================================
    // ===== LeetCode 主接口 =====
    // ============================================================
    public static long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;

        // ===== Step 1: 把 nums1 拷到 arr (1-based) =====
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums1[i];
        }
        build(1, n, 1);

        // ===== Step 2: 算 nums2 的初始总和 =====
        // op 2 不需要"真正维护 nums2", 只需要追踪 sum2
        long sum2 = 0;
        for (int x : nums2) {
            sum2 += x;
        }

        // ===== Step 3: 收集所有 op=3 的结果 =====
        List<Long> results = new ArrayList<>();

        for (int[] q : queries) {
            int op = q[0];
            if (op == 1) {
                // 题目下标从 0 开始, 线段树从 1 开始 -> +1 转换
                int jobl = q[1] + 1;
                int jobr = q[2] + 1;
                reverse(jobl, jobr, 1, n, 1);
            } else if (op == 2) {
                // sum2 += p * (当前 nums1 全部的 1 数量)
                long p = q[1];
                // 当前所有 1 的数量 = 线段树根节点的 sum (即 sum[1])
                // 也可以调 querySum(1, n, 1, n, 1), 但直接读 sum[1] 更快
                long ones = sum[1];
                sum2 += p * ones;
            } else { // op == 3
                results.add(sum2);
            }
        }

        // ===== Step 4: List<Long> -> long[] =====
        long[] ans = new long[results.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = results.get(i);
        }
        return ans;
    }
}