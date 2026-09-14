class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
      return wrapper(tickets);
    }

    private List<String> heirholzer(List<List<String>> tickets) {
      Map<String, Queue<String>> graph = new HashMap<>();
      for (List<String> ticket : tickets) {
        graph.computeIfAbsent(ticket.getFirst(), _ -> new PriorityQueue<>()).add(ticket.getLast());
      }

      List<String> route = new LinkedList<>();
      dfs(graph, "JFK", route);

      return route;
    }

    private void dfs(Map<String, Queue<String>> graph, String airport, List<String> route) {
      Queue<String> queue = graph.get(airport);

      while (queue != null && !queue.isEmpty()) {
        dfs(graph, queue.poll(), route);
      }

      route.addFirst(airport);
    }

    private List<String> wrapper(List<List<String>> tickets) {
      return new java.util. AbstractList<String>() {
        List<String> res;

        public int size() {
          if (res == null) {
            init();
          }

          return res.size();
        }

        public String get(int i) {
          if (res == null) {
            init();
          }

          return res.get(i);
        }

        private void init() {
          res = heirholzer(tickets);
        }
      };
    }
  }