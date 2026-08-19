/*
DP, 状压DP.

最优账单平衡.
给你一个表示交易的数组transactions;
其中transactions[i] = [fromi, toi, amounti];
表示ID = fromi的人给ID = toi的人共计amounti;
请你计算并返回还清所有债务的最小交易笔数.
左程云 算法讲解081【必备】状压dp-下.
测试链接: https://leetcode.cn/problems/optimal-account-balancing/
*/
/*
n = transactions.length:
    TC = O(n * 2^n).
    AS = O(2^n):
        Heap = O(2^n).
        Stack = O(n).
*/
class Solution {
    // 题目说了人员编号的最大范围: 0 ~ 12.
    private static int MAXN = 13;

    public int minTransfers(int[][] transactions) {
        // 统计每个人的最终净债务, 即每个人最后应该收多少钱或付多少钱.
        // 加工出来的debt数组中一定不含有0
        int[] debt = debts(transactions);
        int n = debt.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        /*
        一个零和子集有m个人时, 最少只需要m - 1笔交易:
        ∑(mi ​− 1) = ∑mi − 子集数 = n − 子集数
        */
        return n - f(debt, (1 << n) - 1, 0, n, dp);
    }

    private int[] debts(int[][] transactions) {
        int[] help = new int[MAXN];
        for (int[] transaction : transactions) {
            help[transaction[0]] -= transaction[2];
            help[transaction[1]] += transaction[2];
        }
        int n = 0;
        for (int num : help) {
            if (num != 0) {
                n++;
            }
        }
        int[] debt = new int[n];
        int index = 0;
        for (int num : help) {
            if (num != 0) {
                debt[index++] = num;
            }
        }
        return debt;
    }

    /*
    Divide/decrease and conquer, divide;
    top-down DP, DP is used to store the results of the recursion function:
    dp[set] represents the maximum number of disjoint zero-sum subsets
    that can be formed using the people in 'set'.
    集合set中最多能划分出多少个互不重叠(disjoint)的零和子集(zero-sum subsets).
    */
    private int f(int[] debt, int set, int sum, int n, int[] dp) {
        // Base case
        if (dp[set] != -1) {
            return dp[set];
        }
        // Recursive rule
        int ans = 0;
        if ((set & (set - 1)) != 0) { // 集合中不只一个元素(总是会删除最低位的一个1)
            if (sum == 0) { // 当前set已是一个零和子集, 计入1组后递归处理剩余集合.
                for (int i = 0; i < n; i++) {
                    if ((set & (1 << i)) != 0) {
                        // 找到任何一个元素, 去除这个元素;
                        // 剩下的集合进行尝试, 返回值 + 1.
                        ans = f(debt, set ^ (1 << i), sum - debt[i], n, dp) + 1;
                        // 然后不需要再尝试下一个元素了, 因为答案一定是一样的, 所以直接break.
                        break;
                    }
                }
            } else { // 当前set还不是零和子集, 尝试移除每个元素, 取能形成最多零和子集的最优结果.
                for (int i = 0; i < n; i++) {
                    if ((set & (1 << i)) != 0) {
                        ans = Math.max(ans, f(debt, set ^ (1 << i), sum - debt[i], n, dp));
                    }
                }
            }
        }
        dp[set] = ans;
        return ans;
    }
}