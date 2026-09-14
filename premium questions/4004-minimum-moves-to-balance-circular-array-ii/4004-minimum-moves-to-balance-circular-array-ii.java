import java.util.*;

class Solution {
    public long minMoves(int[] balance) {
        int n = balance.length;
        long S = 0;
        for (int x : balance) S += x;
        if (S < 0) return -1;

        long[] P = new long[Math.max(n - 1, 0)];
        long run = 0;
        for (int i = 0; i < n - 1; i++) {
            run += balance[i];
            P[i] = run;
        }

        long maxAbs = 0;
        for (int x : balance) maxAbs += Math.abs((long) x);
        long lo = -(maxAbs + S + 10), hi = (maxAbs + S + 10);

        while (hi - lo > 2) {
            long m1 = lo + (hi - lo) / 3;
            long m2 = hi - (hi - lo) / 3;
            long g1 = g(P, S, m1);
            long g2 = g(P, S, m2);
            if (g1 < g2) hi = m2;
            else lo = m1;
        }

        long best = Long.MAX_VALUE;
        for (long c = lo; c <= hi; c++) {
            best = Math.min(best, g(P, S, c));
        }
        return best;
    }

    private long g(long[] P, long S, long c) {
        long clampCost = 0;
        long[] ct = new long[P.length];
        for (int i = 0; i < P.length; i++) {
            long t = P[i] + c;
            long clamped = Math.min(Math.max(t, 0), S);
            clampCost += Math.abs(t - clamped);
            ct[i] = clamped;
        }
        long poolCost = isotonicCost(ct);
        return Math.abs(c) + clampCost + poolCost;
    }

    // L1 non-decreasing isotonic regression total cost via single max-heap trick
    private long isotonicCost(long[] arr) {
        PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        long cost = 0;
        for (long x : arr) {
            maxHeap.add(x);
            if (maxHeap.peek() > x) {
                cost += maxHeap.peek() - x;
                maxHeap.poll();
                maxHeap.add(x);
            }
        }
        return cost;
    }
}