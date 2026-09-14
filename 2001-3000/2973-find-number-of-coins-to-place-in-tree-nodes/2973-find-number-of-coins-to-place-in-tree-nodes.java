  class Solution {

    final static long min_inf = Long.MIN_VALUE;
    final static long max_inf = Long.MAX_VALUE;

    record Node(int v, Node next) {
    }

    class child {

      int size;
      long max1;
      long max2;
      long max3;
      long min1;
      long min2;

      child(long val) {
        size = 1;
        max1 = val;
        max2 = min_inf;
        max3 = min_inf;
        min1 = val;
        min2 = max_inf;
      }
    }

    Node[] map;
    long[] ans;
    int[] cost;

    public long[] placedCoins(int[][] edges, int[] cost) {

      this.cost = cost;
      int n = cost.length;

      map = new Node[n];
      ans = new long[n];

      for (int[] e : edges) {
        map[e[0]] = new Node(e[1], map[e[0]]);
        map[e[1]] = new Node(e[0], map[e[1]]);
      }

      dfs(0, -1);
      return ans;
    }

    child dfs(int u, int parent) {

      child cur = new child(cost[u]);

      for (Node node = map[u]; node != null; node = node.next) {

        int v = node.v;
        if (v == parent)
          continue;

        child c = dfs(v, u);
        cur.size += c.size;

        merge(cur, c);
      }

      if (cur.size < 3) {
        ans[u] = 1;
      } else {

        long a = (cur.max2 != min_inf && cur.max3 != min_inf)
            ? cur.max1 * cur.max2 * cur.max3
            : min_inf;

        long b = (cur.min1 != max_inf && cur.min2 != max_inf)
            ? cur.min1 * cur.min2 * cur.max1
            : min_inf;

        long res = Math.max(a, b);
        ans[u] = Math.max(0, res);
      }

      return cur;
    }

    void merge(child a, child b) {
    // handle max
      long[] maxArr = { a.max1, a.max2, a.max3, b.max1, b.max2, b.max3 };

      long max1 = min_inf;
      long max2 = min_inf;
      long max3 = min_inf;

      for (long x : maxArr) {

        if (x == min_inf)
          continue;

        if (x > max1) {
          max3 = max2;
          max2 = max1;
          max1 = x;
        } else if (x > max2) {
          max3 = max2;
          max2 = x;
        } else if (x > max3) {
          max3 = x;
        }
      }

      // handle min
      long[] minArr = { a.min1, a.min2, b.min1, b.min2 };

      long min1 = max_inf;
      long min2 = max_inf;

      for (long x : minArr) {

        if (x == max_inf)
          continue;

        if (x < min1) {
          min2 = min1;
          min1 = x;
        } else if (x < min2) {
          min2 = x;
        }

      }

      a.max1 = max1;
      a.max2 = max2;
      a.max3 = max3;
      a.min1 = min1;
      a.min2 = min2;
    }
  }