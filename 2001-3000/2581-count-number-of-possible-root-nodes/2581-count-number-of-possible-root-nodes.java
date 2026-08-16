class Solution {
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length + 1;
        Set<Integer>[] graph1 = new HashSet[n];
        Set<Integer>[] graph2 = new HashSet[n];
        Map<Integer, Integer> parent = new HashMap<>();
        for(int i = 0; i < n; i++) {
            graph1[i] = new HashSet<>();
            graph2[i] = new HashSet<>();
        }
        for(int[] e : edges) {
            int u = e[0], v = e[1];
            if(u > v) {
                int temp = u;
                u = v;
                v = temp;
            }
            graph1[u].add(v);
            parent.put(v, u);
        }
        int[] correctGuesses =  new int[n];
        for(int[] g : guesses) {
            int u = g[0], v = g[1];
            if(graph1[u].contains(v)) {
                correctGuesses[0]++;
            }
            graph2[u].add(v);
        }
        for(int u = 1; u < n; u++) {
            Integer p = parent.get(u);
            if(p == null) {
                continue;
            }
            if((graph2[u].contains(p) && graph2[p].contains(u)) ||
               (!graph2[u].contains(p) && !graph2[p].contains(u))) {
                correctGuesses[u] = correctGuesses[p];
            } else if(graph2[u].contains(p) && !graph2[p].contains(u)) {
                correctGuesses[u] = correctGuesses[p] + 1;
            } else if(!graph2[u].contains(p) && graph2[p].contains(u)) {
                correctGuesses[u] = correctGuesses[p] - 1;
            }
        }
        int res = 0;
        for(int c : correctGuesses) {
            if(c >= k) {
                res++;
            }
        }
        return res;
    }
}