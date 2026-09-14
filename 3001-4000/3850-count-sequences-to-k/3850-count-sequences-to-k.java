class Solution {
    public int countSequences(int[] nums, long k) {

        int n = nums.length;
        int[] factors = new int[]{2, 3, 5}, need = new int[6];
        for (int x:factors) {
            while (k % x == 0) {
                k /= x;
                need[x]++;
            }
        }

        if(k > 1) return 0;
        
        int[] count = new int[7];
        for (int x : nums)
            ++count[x];
        
        if (need[2] > count[2] + 2 * count[4] + count[6] || need[3] > count[3] + count[6] || need[5] > count[5]) return 0;
        
        // p 数组用于存储各个数字产生特定净贡献的组合方案数（即多项式展开的系数）
        int[][] p = new int[7][];
        for (int x = 2; x <= 6; ++x) {
            // 计算数字 i 产生不同净贡献的方案数
            int c = count[x];
            p[x] = calc(c);
            if (x == 4) {
                // 对于数字 4，每次乘除操作对质因数 2 的幂次影响是正负 2
                // 因此需要将原本步长为 1 的贡献数组展开，把方案数放置在索引跨度为 2 倍的位置上
                int[] q = new int[c << 2 | 1];
                for (int i = -c; i <= c; ++i) 
                    q[(c + i) * 2] = p[x][c + i];
                
                p[x] = q;
            }
        }
        
        // 计算数字 2 和数字 4 对质因数 2 的联合贡献多项式 w
        int[] w = new int[p[2].length + p[4].length - 1];
        for (int i = 0; i < p[2].length; ++i) {
            for (int j = 0; j < p[4].length; ++j) 
                w[i + j] += p[2][i] * p[4][j];
        }
        
        // 处理独立元素的方案数
        // 数字 5 必须恰好凑出 need[5] 的净贡献，加上偏移量 count[5] 防止数组下标为负
        int ans = p[5][need[5] + count[5]];
        // 数字 1 的任何操作都不会改变结果，因此每个数字 1 都有 3 种独立的操作选择
        for (int i = 0; i < count[1]; ++i) 
            ans *= 3;
        
        // ways 用于累加存在耦合关系的数字组合（即 6、3 以及联合起来的 2 和 4）的总方案数
        int ways = 0;
        // 核心解耦：枚举数字 6 产生的净贡献 c6
        for (int c6 = -count[6]; c6 <= count[6]; ++c6) {
            // 根据数字 6 的当前贡献，反推出数字 3 需要凑出的净贡献目标 c3，以及 2 和 4 需要凑出的联合目标 c2
            // 加上各自的零点偏移量以对应数组索引
            int c3 = count[3] + need[3] - c6, c2 = count[2] + 2 * count[4] + need[2] - c6;
            // 校验所需的净贡献是否在合法范围内（即没有超出剩余数字全乘或全除的能力极限）
            if (c3 >= 0 && c3 < p[3].length && c2 >= 0 && c2 < w.length)
                // 根据乘法原理：将合法的方案数相乘，并累加到 ways 中
                ways += p[6][count[6] + c6] * p[3][c3] * w[c2];
        }

        // 将独立部分的方案数与耦合部分的方案数相乘得到最终结果
        ans *= ways;
        return ans;
    }

    // 内部辅助函数：以 O(N) 线性时间倒推计算多项式 (x + 1 + x^(-1))^n 的各项系数
    int[] calc(int n) { 
        if (n == 0) return new int[] {1};
        // 数组长度为 2n + 1，最高次幂为 n，最低次幂为 -n
        int m = 2 * n + 1;
        int[] result = new int[m];
        // 初始化边界条件，对应最高次幂项和次高次幂项的系数
        result[m - 1] = 1;
        result[m - 2] = n;
        // 利用已知的组合数学恒等式，从高向低递推所有次幂的系数
        for (int i = n - 1; i > -n; --i) 
            result[n + i - 1] = ((n + i + 1) * result[n + i + 1] + i * result[n + i]) / (n - i + 1);

        return result;
    }
}