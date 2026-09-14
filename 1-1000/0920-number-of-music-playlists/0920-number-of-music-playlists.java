// /*
// DP.
// */
// /*
// n = n, m = goal:
//     TC = O(n*m).
//     AS = O(n*m):
//         Heap = O(n*m).
//         Stack = O(1).
// */
// class Solution {
//     /*
//     DP; dp[i][j] represents the solution of i songs with j different songs.
//     */
//     public int numMusicPlaylists(int n, int goal, int k) {
//         int mod = (int) Math.pow(10, 9) + 7;
//         long[][] dp = new long[goal + 1][n + 1];
//         // Base case
//         dp[0][0] = 1;
//         // Induction rule
//         for (int i = 1; i <= goal; i++) {
//             for (int j = 1; j <= n; j++) {
//                 // Case 1: Adding a new unique song to the playlist.
//                 // dp[i - 1][j - 1] = number of playlists of length i-1 with j-1 unique songs
//                 // (n - (j - 1)) = number of songs not used yet (available to add as new)
//                 dp[i][j] = (dp[i - 1][j - 1] * (n - (j - 1))) % mod;
//                 if (j > k) {
//                     // Case 2: Adding a repeated song (only allowed if j > k)
//                     // dp[i - 1][j] = playlists of length i-1 with j unique songs
//                     // (j - k) = number of songs eligible for repetition (must not be in the last k songs)
//                     dp[i][j] = (dp[i][j] + (dp[i - 1][j] * (j - k)) % mod) % mod;
//                 }
//             }
//         }
//         return (int) dp[goal][n];
//     }
// }


/*
容斥.

播放列表的数量.
给定三个参数, n, l, k;
你的音乐播放器里有n首不同的歌;
在旅途中你的旅伴想要听l首歌;
听得歌曲不一定不同, 即允许歌曲重复;
请你为她按如下两条规则创建一个播放列表;
1) 每首歌至少播放一次.
2) 一首歌只有在其他k首歌播放完之后才能再次播放.
返回可以满足要求的播放列表的数量;
结果可能很大对1000000007取模.
左程云 算法讲解099【扩展】 逆元和除法同余、容斥原理.
测试链接: https://leetcode.cn/problems/number-of-music-playlists/
*/
/*
n = n, l = l, k = k:
    TC = O((n - k) * log2(l)).
    AS = O(1):
        Heap = O(1).
        Stack = O(1).
*/
class Solution {
    private static int MOD = 1000000007;

    private static int LIMIT = 100;

    private static long[] fac = new long[LIMIT + 1];

    private static long[] inv = new long[LIMIT + 1];

    static {
        fac[0] = 1;
        for (int i = 1; i <= LIMIT; i++) {
            fac[i] = ((long) i * fac[i - 1]) % MOD;
        }
        inv[LIMIT] = power(fac[LIMIT], MOD - 2);
        for (int i = LIMIT - 1; i >= 0; i--) {
            inv[i] = ((long) (i + 1) * inv[i + 1]) % MOD;
        }
    }

    private static long power(long x, int n) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = (ans * x) % MOD;
            }
            x = (x * x) % MOD;
            n >>= 1;
        }
        return ans;
    }

    public int numMusicPlaylists(int n, int l, int k) {
        long cur;
        long ans = 0;
        long sign = 1;
        for (int i = 0; i < n - k; i++, sign = sign == 1 ? (MOD - 1) : 1) {
            cur = (sign * power(n - i - k, l - k)) % MOD;
            cur = (cur * fac[n]) % MOD;
            cur = (cur * inv[i]) % MOD;
            cur = (cur * inv[n - i - k]) % MOD;
            ans = (ans + cur) % MOD;
        }
        return (int) ans;
    }
}