class Solution {

    private int[][] graph;
    private int maxTree;

    private int[][] createGraph(int[][] edges, int n) {
        graph = new int[n][];
        int[] degree = new int[n];

        //create a list of the number of chidlren for each node
        //use the 'degree' array as a pointer to fill in the children of this undirected tree

        for (int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];

            degree[start]++;
            degree[end]++;
        }

        for (int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];

            if (graph[start] == null) {
                graph[start] = new int[degree[start]];
            }
            if (graph[end] == null) {
                graph[end] = new int[degree[end]];
            }

            graph[start][--degree[start]] = end;
            graph[end][--degree[end]] = start;
        }

        return graph;
    }

    private boolean findMaxSubTree(int start, boolean[] visited, int[] colors, int[] sizes) {

        if (visited[start])
            return false;

        visited[start] = true;
        boolean isValid = true;
        sizes[start] = 1;

        for (int child : graph[start]) {

            if (visited[child]) // already visited, check next child
                continue;

            boolean childValid = findMaxSubTree(child, visited, colors, sizes);

            // child subtree must be valid for the current subtree to be also valid
            if (!childValid || colors[child] != colors[start]) {
                isValid = false;
            }

            else {
                //only if the sub tree is valid, add the size of the child subree to the parent
                sizes[start] += sizes[child];
            }

        }

        if (isValid)
            maxTree = Math.max(maxTree, sizes[start]); // determine if the subtree we just processed is a bigger sub tree than the current max

        return isValid;

    }

    public int maximumSubtreeSize(int[][] edges, int[] colors) {
        if (edges.length == 0)
            return 1;
        graph = createGraph(edges, colors.length);
        maxTree = 0;
        findMaxSubTree(0, new boolean[colors.length], colors, new int[colors.length]);
        return maxTree;

    }
}