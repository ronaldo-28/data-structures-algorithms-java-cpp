class Solution {
    public int[] minEdgeReversals(int n, int[][] edges) {
        // having tree formed - count flips. (assign state to each child)
        // then for each of nodes - make it root, count flips
        // other approach: 
        // having tree with root somewhere - for each node compute required flips, top down
        int[] parents = new int[n];
        Arrays.fill(parents, -1);
        
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            pinch(parents, edge[0]);
            parents[edge[0]] = edge[1];
        }

        int[] states = new int[n];
        int flips = 0;
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            if (parents[edge[0]] == edge[1]) {
                states[edge[0]] = 1;
                flips ++;
            } else {
                states[edge[1]] = 0;
                
            }
        }

      //  System.out.println(Arrays.toString(parents));
      //  System.out.println(Arrays.toString(states));

        int[] counts = new int[n]; 
        for (int i = 0; i < n; i++) {
            if (parents[i] != -1) counts[parents[i]]++;
        }

        int[] order = new int[n]; int p = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 0) order[p++] = i; 
        }
        for (int i = 0; i < p; i++) {
            int current = order[i];
            int par = parents[current];
            if (par != -1) {
                if (-- counts[par] == 0) order[p++] = par;
            }
        }

        int[] result = new int[n];
        for (int i = n-1; i >= 0; i--) {
            int current = order[i];
            if (parents[current] != -1) {
                flips = result[parents[current]];
                if (states[current] == 1) {
                    flips --;
                } else {
                    flips ++;
                }
            }
            result[current] = flips;
        }

        return result;

    }

    void pinch(int[] parents, int p) {
        // make p root;
        int parent = parents[p];
        if (parent == -1) return;
        pinch(parents, parent);
        parents[parent] = p;
        parents[p] = -1;
    }
}