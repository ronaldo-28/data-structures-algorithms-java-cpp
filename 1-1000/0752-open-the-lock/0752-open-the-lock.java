import java.util.*;

class Solution {
    private static final int[] P10 = {1, 10, 100, 1000};

    public int openLock(String[] deadends, String target) {
        boolean[] dead = new boolean[10000];
        for (String s : deadends) dead[toInt(s)] = true;

        int tgt = toInt(target);
        if (dead[0] || dead[tgt]) return -1;
        if (tgt == 0) return 0;

        // två fronter (köer) + seen för respektive sida
        int[] fq = new int[10000], bq = new int[10000];
        int fh = 0, ft = 0, bh = 0, bt = 0;
        boolean[] fSeen = new boolean[10000], bSeen = new boolean[10000];

        fq[ft++] = 0;      fSeen[0] = true;
        bq[bt++] = tgt;    bSeen[tgt] = true;

        int steps = 0;
        while (fh < ft && bh < bt) {
            // expandera den mindre fronten
            if (ft - fh > bt - bh) {
                int[] tq = fq; fq = bq; bq = tq;
                int th = fh; fh = bh; bh = th;
                int tt = ft; ft = bt; bt = tt;
                boolean[] ts = fSeen; fSeen = bSeen; bSeen = ts;
            }

            int size = ft - fh;
            for (int s = 0; s < size; s++) {
                int cur = fq[fh++];
                if (bSeen[cur]) return steps; // möttes
                for (int i = 0; i < 4; i++) {
                    int d = (cur / P10[i]) % 10;

                    // +1 på hjul i
                    int up = cur + ((d == 9 ? -9 : 1) * P10[i]);
                    if (!dead[up] && !fSeen[up]) {
                        if (bSeen[up]) return steps + 1;
                        fSeen[up] = true;
                        fq[ft++] = up;
                    }

                    // -1 på hjul i
                    int dn = cur + ((d == 0 ? 9 : -1) * P10[i]);
                    if (!dead[dn] && !fSeen[dn]) {
                        if (bSeen[dn]) return steps + 1;
                        fSeen[dn] = true;
                        fq[ft++] = dn;
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    private int toInt(String s) {
        return (s.charAt(0) - '0') * 1000
             + (s.charAt(1) - '0') * 100
             + (s.charAt(2) - '0') * 10
             + (s.charAt(3) - '0');
    }
}