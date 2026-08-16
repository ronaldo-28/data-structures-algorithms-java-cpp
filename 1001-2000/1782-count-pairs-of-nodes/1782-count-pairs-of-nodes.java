class Solution {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        // (1,3) (2,4) (3,2) (4,1)
        // (4,1) (3,2) (1,3) (2,4)
        int[] count = new int[n];
        int[] deg = new int[n];
        for(int[] e: edges) {
            int u = e[0] - 1;
            int v = e[1] - 1;
            count[u]++;
            count[v]++;
            deg[u]++;
            deg[v]++;
        }
        Arrays.sort(count);
        int q = queries.length;
        int[] ans = new int[q];
        System.out.println(Arrays.toString(count));
        for(int i = 0; i < q; i++) {
            int curr = queries[i];
            int r = n-1;
            for(int l = 0; l < n-1; l++) {
                r = Math.max(r, l + 1);
                while(count[l] + count[r] > curr) {
                    if(r - 1 > l && count[l] + count[r-1] > curr) {
                        r--;
                    }
                    else {
                        break;
                    }
                }
                if(count[l] + count[r] > curr) {
                    ans[i] += n - r;
                }
            }
        }
        // corrections
        // deg[i] + deg[j] = x
        // incident[i][j] = x - k where k = num edges between i and j
        // we need to subtract 1 for every query with value [x - k, x-1]
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] e: edges) {
            int u = e[0] - 1;
            int v = e[1] - 1;
            int mapping = v * 20001 + u;
            if(u > v) {
                mapping = u * 20001 + v;
            }
            map.put(mapping, map.getOrDefault(mapping, 0) + 1);
        }
        for(int mapped: map.keySet()) {
            int v = mapped/20001;
            int u = mapped % 20001;
            int freq = map.get(mapped);
            int x = deg[u] + deg[v];
            int actual = x - freq;
            // subtract 1 from every query between actual and x-1
            for(int i = 0; i < q; i++) {
                if(queries[i] < x && queries[i] >= actual) {
                    ans[i]--;
                }
            }
        }
        return ans;
    }
}