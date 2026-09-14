// enum Color { kInit, kRed, kBlue }

// class Solution {
//   public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
//     int[] ans = new int[n];
//     Arrays.fill(ans, -1);
//     // graph[u] := [(v, edgeColor)]
//     List<Pair<Integer, Color>>[] graph = new List[n];
//     // [(u, prevColor)]
//     Queue<Pair<Integer, Color>> q = new ArrayDeque<>(Arrays.asList(new Pair<>(0, Color.kInit)));

//     for (int i = 0; i < n; ++i)
//       graph[i] = new ArrayList<>();

//     for (int[] edge : redEdges) {
//       final int u = edge[0];
//       final int v = edge[1];
//       graph[u].add(new Pair<>(v, Color.kRed));
//     }

//     for (int[] edge : blueEdges) {
//       final int u = edge[0];
//       final int v = edge[1];
//       graph[u].add(new Pair<>(v, Color.kBlue));
//     }

//     for (int step = 0; !q.isEmpty(); ++step)
//       for (int sz = q.size(); sz > 0; --sz) {
//         final int u = q.peek().getKey();
//         Color prevColor = q.poll().getValue();
//         ans[u] = ans[u] == -1 ? step : ans[u];
//         for (int i = 0; i < graph[u].size(); ++i) {
//           Pair<Integer, Color> node = graph[u].get(i);
//           final int v = node.getKey();
//           Color edgeColor = node.getValue();
//           if (v == -1 || edgeColor == prevColor)
//             continue;
//           q.add(new Pair<>(v, edgeColor));
//           // Mark (u, v) as used.
//           graph[u].set(i, new Pair<>(-1, edgeColor));
//         }
//       }

//     return ans;
//   }
// }

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) { 
        int[] red = new int[n];
        int[] blue = new int[n];
        Arrays.fill(red, Integer.MAX_VALUE);
        Arrays.fill(blue, Integer.MAX_VALUE);
        red[0] = 0;
        blue[0] = 0;
        boolean updated = true;

        while(updated) {
            int[] redTemp = Arrays.copyOf(red, n);
            int[] blueTemp = Arrays.copyOf(blue, n);

            updated = update(redEdges, redTemp, blue) || update(blueEdges, blueTemp, red);

            if(updated) {
                red = Arrays.copyOf(redTemp, n);
                blue = Arrays.copyOf(blueTemp, n);
            }
        }

        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            int best = Math.min(red[i], blue[i]);
            ans[i] = best == Integer.MAX_VALUE ? -1 : best;
        }
        return ans;
    }

    public boolean update(int[][] edges, int[] best, int[] bestIncoming) {
        boolean updated = false;
        for(int[] edge : edges) {
            int origin = edge[0];
            int dest = edge[1];
            int len = bestIncoming[origin];
            if(len != Integer.MAX_VALUE && len + 1 < best[dest]) {
                best[dest] = len + 1;
                updated = true;
            }
        }
        return updated;
    }
}

/*
There are N nodes, numbered 0 through N-1.
Edges are directed, each is either red or blue.
May be self edges and/or parallel edges.
Find length of shortest path with alternating colors from 0 to each node.
 Best red-first path:
  Best red-first path from 0 to mid + best next path from mid to target

Bellman-Ford(?) Algorithm
 red end:  [0, -1, -1, ..., -1]
 blue end: [0, -1, -1, ..., -1]

For each iteration:
 Copy previous red/blue arrays
 For each red edge (a,b):
  New possible best red-ending is previous blue-ending plus 1.
  If no improvement, we're done
*/