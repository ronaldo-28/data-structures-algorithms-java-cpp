// https://www.youtube.com/@0x3f
class Solution {
    public long totalWaviness(long num1, long num2) {
        return calc(num2) - calc(num1 - 1);
    }

    // 计算 [1, n] 中的整数的波动值之和
    private long calc(long n) {
        long ans = 0;

        // 把整数划分成五段：prefix | l | m | r | suffix
        // 从低到高枚举 (l, m, r) 的位置，计算 (l, m, r) 对答案的贡献
        for (long pow10 = 1; n >= pow10 * 100; pow10 *= 10) {
            long maxPrefix = n / (pow10 * 1000);
            long n2 = n / pow10;
            int L = (int) (n2 / 100 % 10);
            int M = (int) (n2 / 10 % 10);
            int R = (int) (n2 % 10);

            // 1. prefix < maxPrefix 时，低位不受约束
            // 但 prefix=0 且 l=0 的情况是不合法的，需要减掉
            long cnt = maxPrefix * 570 - 45;  // 先不与 pow10 相乘

            // 2. prefix = maxPrefix 且 l < L
            cnt += (121 + L * 15 - L * L) * L / 3;

            // 3. prefix = maxPrefix 且 l = L 且 m < M
            cnt += (L + M) * Math.max(M - L - 1, 0) / 2;       // 峰
            cnt += (19 - Math.min(L, M)) * Math.min(L, M) / 2; // 谷

            // 4. prefix = maxPrefix 且 l = L 且 m = M 且 r < R
            if (L < M) { // 只能是峰
                cnt += Math.min(M, R);
            } else if (L > M) { // 只能是谷
                cnt += Math.max(R - M - 1, 0);
            }

            // 到此为止，suffix 可以随便填，有 pow10 种填法
            ans += cnt * pow10;

            // 5. prefix = maxPrefix 且 l = L 且 m = M 且 r = R
            if ((L - M) * (M - R) < 0) { // 峰或谷
                long maxSuffix = n % pow10;
                ans += maxSuffix + 1; // suffix 可以填 [0, maxSuffix] 中的任意整数
            }
        }

        return ans;
    }
}