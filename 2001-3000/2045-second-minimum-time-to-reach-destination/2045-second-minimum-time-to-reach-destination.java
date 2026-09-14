import java.util.*;

  class Solution {
    record Node(int v, Node next) {
    }

    Node[] g;

    public int secondMinimum(int n, int[][] edges, int time, int change) {

      g = new Node[n + 1];

      for (int[] e : edges) {
        g[e[0]] = new Node(e[1], g[e[0]]);
        g[e[1]] = new Node(e[0], g[e[1]]);
      }

      int[] dist1 = new int[n + 1];
      int[] dist2 = new int[n + 1];
      Arrays.fill(dist1, -1);
      Arrays.fill(dist2, -1);

      Deque<int[]> queue = new ArrayDeque<>();
      queue.offer(new int[] { 1, 0 });
      dist1[1] = 0;

      while (!queue.isEmpty()) {
        int[] curr = queue.poll();
        int u = curr[0];
        int steps = curr[1];
        for (var node = g[u]; node != null; node = node.next) {
          int v = node.v;

          int nextSteps = steps + 1;

          if (dist1[v] == -1) {
            dist1[v] = nextSteps;
            queue.offer(new int[] { v, nextSteps });
          }

          else if (dist2[v] == -1 && nextSteps > dist1[v]) {
            dist2[v] = nextSteps;
            if (v == n)
              return calculateTime(nextSteps, time, change);
            queue.offer(new int[] { v, nextSteps });
          }
        }
      }
      return 0;
    }

    private int calculateTime(int steps, int time, int change) {
      int totalTime = 0;
      for (int i = 0; i < steps; i++) {

        if ((totalTime / change) % 2 == 1) {
          totalTime += change - (totalTime % change);
        }
        totalTime += time;
      }
      return totalTime;
    }
  }