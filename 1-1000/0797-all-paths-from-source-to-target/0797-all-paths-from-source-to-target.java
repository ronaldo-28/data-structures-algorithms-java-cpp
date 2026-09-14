class Solution {

    int destination; 

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        destination = graph.length-1;

        List<List<Integer>> result = new ArrayList<>();
        dfs(graph, 0, new ArrayList<>(), result);

        return result;
    }

    public void dfs(int[][] graph, int current, List<Integer> temp, List<List<Integer>> result) {
        
        temp.add(current);

        if (current == destination) {
            result.add(new ArrayList<>(temp));
            temp.remove(temp.size()-1);
            return;
        }

        for (int neighbor : graph[current]) {
            dfs(graph, neighbor, temp, result);
        }

        temp.remove(temp.size()-1);
    }
}