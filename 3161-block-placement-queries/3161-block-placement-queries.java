class Solution {
    public List<Boolean> getResults(int[][] queries) {
        int max = 0;
        for(int[] q: queries)
            max = Math.max(max, q[1]);
        max += 2;
        boolean[] obs = new boolean[max+1];
        obs[0] = obs[max] = true;
        for(int[] q: queries)
            if(q[0] == 1)
                obs[q[1]] = true;
        int[] left = new int[max+1];
        int[] right = new int[max+1];
        for(int i = 0; i <= max; i++) 
            left[i] = obs[i] ? i : left[i-1];
        for(int i = max; i > -1; i--) 
            right[i] = obs[i] ? i : right[i+1];

        BIT bit = new BIT(max);
        int p = 0;
        for(int i = 1; i <= max; i++)
            if(obs[i]) {
                bit.update(i, i - p);
                p = i;
            }

        List<Boolean> res = new ArrayList<> ();
        for(int i = queries.length-1; i > -1; i--) {
            int[] q = queries[i];
            if(q[0] == 1) {
                int x = q[1];
                int prev = find(x-1, left);
                int nxt = find(x+1, right);
                bit.update(nxt, nxt - prev);

                obs[x] = false;
                left[x] = prev;
                right[x] = nxt;
            }
            else {
                int x = q[1], sz = q[2];
                int prev = find(x, left);
                int gap = x - prev;
                int maxGap = bit.query(prev);
                res.add(Math.max(maxGap, gap) >= sz);
            }
        }
        Collections.reverse(res);
        return res;
    }

    public int find(int x, int[] arr) {
        int root = x;
        while(arr[root] != root)
            root = arr[root];

        while(x != root) {
            int nxt = arr[x];
            arr[x] = root;
            x = nxt;
        }
        return root;
    }

    public class BIT {
        int[] arr;
        int sz;

        public BIT(int sz) {
            this.sz = sz + 1;
            arr = new int[this.sz];
        }

        public void update(int x, int v) {
            if(x <= 0)
                return;
            while(x < sz) {
                arr[x] = Math.max(arr[x], v);
                x += lowbit(x);
            }
        }

        public int query(int x) {
            int res = 0;
            while(x > 0) {
                res = Math.max(res, arr[x]);
                x -= lowbit(x);
            }
            return res;
        }

        public int lowbit(int x) {
            return x & -x;
        }
    }
}