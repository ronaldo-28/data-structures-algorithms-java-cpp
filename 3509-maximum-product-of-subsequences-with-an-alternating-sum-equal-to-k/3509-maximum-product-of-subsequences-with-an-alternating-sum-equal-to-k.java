class Solution {
    public int maxProduct(int[] nums, int k, int limit) {
        final int n = nums.length;

        // ---- 1. Tight bounds on the alternating sum (counting sort, values 0..12) ----
        int[] cnt = new int[13];
        for (int v : nums) cnt[v]++;
        int hiCnt = (n + 1) >>> 1, loCnt = n >>> 1;       // max #added / #subtracted elements
        int hiB = 0, loB = 0;
        for (int v = 12, a = hiCnt, b = loCnt; v > 0; v--) {
            int c = cnt[v];
            int ta = Math.min(a, c); hiB += ta * v; a -= ta;
            int tb = Math.min(b, c); loB += tb * v; b -= tb;
        }
        if (k > hiB || k < -loB) return -1;

        // ---- 2. Compress products: 0, every reachable product <= limit, OVER ----
        boolean[] reach = new boolean[limit + 1];
        if (cnt[1] > 0) reach[1] = true;
        for (int v = 2; v <= 12 && v <= limit; v++) if (cnt[v] > 0) reach[v] = true;
        for (int p = 2; p <= limit; p++) {
            if (!reach[p]) continue;
            for (int v = 2; v <= 12; v++) {
                if (cnt[v] == 0) continue;
                int q = p * v;
                if (q > limit) break;
                reach[q] = true;
            }
        }
        int[] idxOf = new int[limit + 1];
        int[] val = new int[limit + 2];
        int P = 1;                                   // index 0 == product 0
        for (int p = 1; p <= limit; p++) if (reach[p]) { idxOf[p] = P; val[P++] = p; }
        final int OVER = P++;                        // product > limit (can still become 0)

        // mul[x * P + i] = index of (val[i] * x)
        int[] mul = new int[13 * P];
        for (int x = 0; x <= 12; x++) {
            if (cnt[x] == 0) continue;
            int base = x * P;
            for (int i = 1; i < P; i++) {
                int r;
                if (x == 0) r = 0;
                else if (i == OVER) r = OVER;
                else { int q = val[i] * x; r = q > limit ? OVER : idxOf[q]; }
                mul[base + i] = r;
            }
        }

        // ---- 3. Bitset DP: row (parity, product) holds a bitset over alternating sums ----
        final int off = loB;
        final int W = (loB + hiB + 64) >>> 6;
        final int rows = P * W;
        long[] dp = new long[2 * rows];              // parity 0 rows first, then parity 1
        long[] tmp = new long[rows];                 // snapshot of parity-0 rows
        boolean[] act = new boolean[2 * P];
        boolean[] actTmp = new boolean[P];

        int pre = 0, suf = 0;
        for (int v : nums) suf += v;
        for (int x : nums) {
            pre += x;
            suf -= x;
            // only sums that are reachable so far AND can still be turned into k matter
            int lo = Math.max(off - Math.min(pre, loB), off + k - suf);
            int hi = Math.min(off + Math.min(pre, hiB), off + k + suf);
            if (lo <= hi) {
                int mb = x * P;
                int loW = lo >>> 6, hiW = hi >>> 6;
                int cLo = loW > 0 ? loW - 1 : 0, cLen = Math.min(hiW + 1, W - 1) - cLo + 1;

                // snapshot old parity-0 rows (native memcpy of the live window only)
                System.arraycopy(act, 0, actTmp, 0, P);
                for (int i = 0; i < P; i++)
                    if (actTmp[i]) System.arraycopy(dp, i * W + cLo, tmp, i * W + cLo, cLen);

                // phase A: odd length -> append at odd index (sum -= x) -> even length
                for (int i = 0; i < P; i++) {
                    if (!act[P + i]) continue;
                    int t = mul[mb + i];
                    shiftOr(dp, rows + i * W, dp, t * W, loW, hiW, W, -x);
                    act[t] = true;
                }
                // phase B: even length (old snapshot) -> append at even index (sum += x) -> odd length
                for (int i = 0; i < P; i++) {
                    if (!actTmp[i]) continue;
                    int t = mul[mb + i];
                    shiftOr(tmp, i * W, dp, rows + t * W, loW, hiW, W, x);
                    act[P + t] = true;
                }
            }
            // start a new subsequence [x]
            int t = x == 0 ? 0 : (x > limit ? OVER : idxOf[x]);
            int pos = off + x;
            dp[rows + t * W + (pos >>> 6)] |= 1L << pos;
            act[P + t] = true;
        }

        // ---- 4. Largest valid product whose bitset contains k ----
        int pos = off + k, w = pos >>> 6;
        long bit = 1L << pos;
        for (int i = OVER - 1; i >= 0; i--) {
            if ((act[i] && (dp[i * W + w] & bit) != 0)
                    || (act[P + i] && (dp[rows + i * W + w] & bit) != 0)) return val[i];
        }
        return -1;
    }

    // d[dOff + w] |= (s[sOff ..] shifted by sh bits) for words w in [loW, hiW]
    private static void shiftOr(long[] s, int sOff, long[] d, int dOff, int loW, int hiW, int W, int sh) {
        if (sh == 0) {
            for (int w = loW; w <= hiW; w++) d[dOff + w] |= s[sOff + w];
        } else if (sh > 0) {                          // towards larger sums
            int r = 64 - sh;
            for (int w = hiW; w > loW; w--)
                d[dOff + w] |= (s[sOff + w] << sh) | (s[sOff + w - 1] >>> r);
            d[dOff + loW] |= loW > 0 ? (s[sOff + loW] << sh) | (s[sOff + loW - 1] >>> r) : s[sOff] << sh;
        } else {                                      // towards smaller sums
            int l = -sh, r = 64 - l;
            for (int w = loW; w < hiW; w++)
                d[dOff + w] |= (s[sOff + w] >>> l) | (s[sOff + w + 1] << r);
            d[dOff + hiW] |= hiW < W - 1 ? (s[sOff + hiW] >>> l) | (s[sOff + hiW + 1] << r) : s[sOff + hiW] >>> l;
        }
    }
}