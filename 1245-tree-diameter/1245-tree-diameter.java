class Solution {
    private int answer;

    private int[][] createGraph(int[][] edges) {
        int[][] graph = new int[edges.length + 1][];
        int[] parents = new int[edges.length + 1];

        for (int i = 0; i < edges.length; i++) {

            int start = edges[i][0];
            int end = edges[i][1];

            parents[start]++;
            parents[end]++;
        }

        for (int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];

            if (graph[start] == null) {
                graph[start] = new int[parents[start]];
            }
            if (graph[end] == null) {
                graph[end] = new int[parents[end]];
            }
            graph[start][--parents[start]] = end;
            graph[end][--parents[end]] = start;
        }

        return graph;
    }

    private int findTreeDiameter(int[][] graph, int currNode, int parentNode) {

        if(graph[currNode] == null) return 0;

        int longestPath = 0;
        int secondLongest = 0;

        for (int child : graph[currNode]) {

            if (child == parentNode)
                continue;

            int childPath = findTreeDiameter(graph, child, currNode);
            if (childPath > longestPath) {
                secondLongest = longestPath;
                longestPath = childPath;
            } else if (childPath > secondLongest) {
                secondLongest = childPath;
            }
        }

        answer = Math.max(answer, longestPath + secondLongest);

        return longestPath + 1;
    }

    public int treeDiameter(int[][] edges) {
        int[][] graph = createGraph(edges);
        answer = 0;
        findTreeDiameter(graph, 0, -1);
        return answer;

    }
}