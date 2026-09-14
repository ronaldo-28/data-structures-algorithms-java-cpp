class Solution {
    // METHOD - 2 : DSU OPTIMISED
    class DisjointSet {
        int[] rank;
        int[] parent;
        DisjointSet(int n) {
            rank = new int[n];
            parent = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int findUnivParent(int u) {
            if(u == parent[u]) return u;

            return parent[u] = findUnivParent(parent[u]);
        }
        public void unionByRank(int u, int v) {
            int ulp_u = findUnivParent(u);
            int ulp_v = findUnivParent(v);

            if(ulp_u == ulp_v) return;

            if(rank[ulp_u] > rank[ulp_v]) {
                parent[ulp_v] = ulp_u;
            } else if(rank[ulp_u] < rank[ulp_v]) {
                parent[ulp_u] = ulp_v;
            } else {
                parent[ulp_v] = ulp_u;
                rank[ulp_u]++;
            }
        }
    }
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        DisjointSet ds = new DisjointSet(n);
        boolean[] result = new boolean[requests.length];

        boolean[][] isEnemy = new boolean[n][n];
        for(int[] res : restrictions) {
            isEnemy[res[0]][res[1]] = true;
            isEnemy[res[1]][res[0]] = true;
        }

        for(int i = 0; i < requests.length; i++) {
            int parentOfU = ds.findUnivParent(requests[i][0]);
            int parentOfV = ds.findUnivParent(requests[i][1]);
            if(parentOfU == parentOfV) {
                result[i] = true;
                continue;
            }

            if(isEnemy[parentOfU][parentOfV]) {
                result[i] = false;
                continue;
            }

            ds.unionByRank(parentOfU, parentOfV);
            result[i] = true;

            int newParent = ds.findUnivParent(parentOfU);
            int oldParent = (newParent == parentOfU) ? parentOfV : parentOfU;
            for(int j = 0; j < n; j++) {
                if(isEnemy[oldParent][j]) {
                    isEnemy[newParent][j] = true;
                    isEnemy[j][newParent] = true;
                }
            }
        }

        return result;
    }
}