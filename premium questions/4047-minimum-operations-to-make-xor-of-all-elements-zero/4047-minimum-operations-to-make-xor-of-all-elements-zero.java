class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int xorAll = 0;
        for (int v : nums) xorAll ^= v;
        if (xorAll == 0) return 0;

        boolean allEqual = true;
        for (int i = 1; i < n; i++) {
            if (nums[i]!= nums[0]) { allEqual = false; break; }
        }
        if (allEqual) return -1;

        if (n <= 11) {
            if (computeRank(nums) == n) return -1;
        }

        boolean[] seen = new boolean[2048];
        List<Integer> distinct = new ArrayList<>();
        for (int v : nums) {
            if (!seen[v]) {
                seen[v] = true;
                distinct.add(v);
            }
        }

        int[] dist = new int[2048];
        Arrays.fill(dist, -1);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        dist[0] = 0;
        q.add(0);
        while (!q.isEmpty()) {
            int u = q.poll();
            int du = dist[u];
            for (int v : distinct) {
                int w = u ^ v;
                if (dist[w] == -1) {
                    dist[w] = du + 1;
                    if (w == xorAll) return dist[w];
                    q.add(w);
                }
            }
        }
        return -1;
    }

    private int computeRank(int[] nums) {
        int[] basis = new int[11];
        int r = 0;
        for (int x : nums) {
            int v = x;
            for (int b = 10; b >= 0; b--) {
                if ((v & (1 << b)) == 0) continue;
                if (basis[b] == 0) {
                    basis[b] = v;
                    r++;
                    break;
                }
                v ^= basis[b];
            }
        }
        return r;
    }
}