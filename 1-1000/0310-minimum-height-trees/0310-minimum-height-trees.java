import java.util.AbstractList;
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) {
          return Collections.singletonList(0);
        }
        if(n == 2) {
          return List.of(0, 1);
        }   
        return wrapper(n, edges);
    }
    private List<Integer> wrapper(int n, int[][] edges) {
      return new AbstractList<Integer>() {
        List<Integer> res;
        public int size() {
          if(res == null) {
            init();
          }
          return res.size();
        }
        public Integer get(int i) {
          if(res == null) {
            init();
          }
          return res.get(i);
        }
        private void init() {
          res = leaves(n, edges);
        }
      };
    }
    private List<Integer> leaves(int n, int[][] edges) { 
      List<Integer>[] graph = new List[n];
      for(int i = 0; i < n; i++) {
        graph[i] = new ArrayList<>();
      }
      int[] indegree = new int[n];
      for(int[] edge: edges) {
        int a = edge[0];
        int b = edge[1];
        graph[a].add(b);
        graph[b].add(a);
        indegree[a]++;
        indegree[b]++;
      }
      Queue<Integer> leaves = new LinkedList<>();
      for(int i = 0; i < n; i++) {
        if(indegree[i] == 1) {
          leaves.add(i);
        }
      }
      int remainingNodes = n;
      while(remainingNodes > 2) {
        int size = leaves.size();
        remainingNodes -= size;
        while(size-- > 0) {
          int leaf = leaves.poll();
          for(int ngb: graph[leaf]) {
            if(--indegree[ngb] == 1) {
              leaves.add(ngb);
            }
          }
        }
      }
      return (List<Integer>) leaves;
    }
    private List<Integer> diameter(int n, int[][] edges) {
      List<Integer>[] graph = new List[n];
      for(int i = 0; i < n; i++) {
        graph[i] = new ArrayList<>();
      }
      for(int[] edge: edges) {
        int a = edge[0];
        int b = edge[1];
        graph[a].add(b);
        graph[b].add(a);
      }
      int[] parent = new int[n];
      int[] node = farthestNodeDfs(graph, 0, new boolean[n], parent);
      int vertex = node[0];
      Arrays.fill(parent, -1);
      node = farthestNodeDfs(graph, vertex, new boolean[n], parent);
      List<Integer> diameter = pathReversed(parent, vertex, node[0]);
      int size = diameter.size();
      List<Integer> res = new ArrayList<>();
      res.add(diameter.get(size / 2));
      if(size % 2 == 0) {
        res.add(diameter.get(size / 2 - 1));
      }
      return res;
    }
    private List<Integer> pathReversed(int[] parent, int start, int end) {
      List<Integer> path = new ArrayList<>();
      for(int cur = end; cur != -1; cur = parent[cur]) {
        path.add(cur);
      }
      return path;
    }
    private int[] farthestNodeBfs(List<Integer>[] graph, int vertex, boolean[] seen, int[] parent) {
      Queue<Integer> queue = new ArrayDeque<>();
      queue.offer(vertex);
      seen[vertex] = true;
      int node = vertex;
      while(!queue.isEmpty()) {
        node = queue.poll();
        for(int ngb: graph[node]) {
          if(seen[ngb]) {
            continue;
          }
          seen[ngb] = true;
          parent[ngb] = node;
          queue.offer(ngb);
        }
      }
      return new int[] {node};
    }
    private int[] farthestNodeDfs(List<Integer>[] graph, int vertex, boolean[] seen, int[] parent) {
      seen[vertex] = true;
      int[] max = new int[] {vertex, 0};
      for(int ngb: graph[vertex]) {
        if(seen[ngb]) {
          continue;
        }
        parent[ngb] = vertex;
        int[] candidate = farthestNodeDfs(graph, ngb, seen, parent);
        if(++candidate[1] > max[1]) { 
          max = candidate;
        }
      }
      return max;
    }
}