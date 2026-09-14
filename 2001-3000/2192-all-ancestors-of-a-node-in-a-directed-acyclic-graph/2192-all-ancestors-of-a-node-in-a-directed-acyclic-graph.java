  class Solution {

    record Node(int v, Node next) {
    }

    Node[] g;

    public List<List<Integer>> getAncestors(int n, int[][] edges) {

      g = new Node[n];
      int[] indeg = new int[n];

      // Build graph
      for (int[] e : edges) {
        int u = e[0], v = e[1];
        g[u] = new Node(v, g[u]);
        indeg[v]++;
      }

      // BitSet for ancestors
      BitSet[] anc = new BitSet[n];
      for (int i = 0; i < n; i++) {
        anc[i] = new BitSet(n);
      }

      // Topo queue
      int[] queue = new int[n];
      int left = 0;
      int right = -1;

      for (int i = 0; i < n; i++) {
        if (indeg[i] == 0)
          queue[++right]=i;
      }

      // Kahn's Algorithm
      while (right-left>=0) {

        int u = queue[left++];

        for (Node node = g[u]; node != null; node = node.next) {

          int v = node.v;

          // inherit ancestors
          anc[v].or(anc[u]);

          // add parent
          anc[v].set(u);

          if (--indeg[v] == 0)
            queue[++right] = v;
        }
      }

      // Build result
      List<List<Integer>> res = new ArrayList<>();

      for (int i = 0; i < n; i++) {
        List<Integer> list = new ArrayList<>();

        for (int b = anc[i].nextSetBit(0); b >= 0; b = anc[i].nextSetBit(b + 1)) {
          list.add(b);
        }

        res.add(list);
      }

      return res;
    }
  }