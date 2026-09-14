class UnionFind {
    private int[] Parent;
    private int[] Size;

    public UnionFind(int n) {
        Parent = new int[n + 1];
        Size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            Parent[i] = i;
            Size[i] = 1;
        }
    }

    public int find(int node) {
        if (Parent[node] != node) {
            Parent[node] = find(Parent[node]);
        }
        return Parent[node];
    }

    public boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        if (pu == pv) {
            return false;
        }
        if (Size[pu] < Size[pv]) {
            int temp = pu;
            pu = pv;
            pv = temp;
        }
        Size[pu] += Size[pv];
        Parent[pv] = pu;
        return true;
    }
}

public class Solution {
    public boolean canTraverseAllPairs(int[] nums) {
        int N = nums.length;
        if (N == 1) {
            return true;
        }
        int MAX = 0;
        for (int num : nums) {
            MAX = Math.max(MAX, num);
            if (num == 1) {
                return false;
            }
        }

        int[] sieve = new int[MAX + 1];
        for (int p = 2; p * p <= MAX; p++) {
            if (sieve[p] == 0) {
                for (int composite = p * p; composite <= MAX; composite += p) {
                    sieve[composite] = p;
                }
            }
        }

        UnionFind uf = new UnionFind(N + MAX + 1);
        for (int i = 0; i < N; i++) {
            int num = nums[i];
            if (sieve[num] == 0) { // num is prime
                uf.union(i, N + num);
                continue;
            }

            while (num > 1) {
                int prime = sieve[num] != 0 ? sieve[num] : num;
                uf.union(i, N + prime);
                while (num % prime == 0) {
                    num /= prime;
                }
            }
        }

        int root = uf.find(0);
        for (int i = 1; i < N; i++) {
            if (uf.find(i) != root) {
                return false;
            }
        }
        return true;
    }
}