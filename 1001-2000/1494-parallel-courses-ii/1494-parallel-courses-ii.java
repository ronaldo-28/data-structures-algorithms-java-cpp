class Solution {
 public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] graph = new int[n];
        int[] outDegree = new int[n];

        for (int[] depend : dependencies){
            int a = depend[0];
            int b = depend[1];
            graph[b - 1] += 1 << (a-1);
            outDegree[a-1] += 1;
        }
        
        int[] p2 = new int[n];
        
        for (int i = 0; i < n; i++)
            p2[i] = 1 << i;
        
        int start = 0;
        int goal = (1 << n) - 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});
        boolean[] visited = new boolean[goal + 1];


        while (!q.isEmpty()){
            int[] cur = q.poll();
            int node = cur[0];
            int steps = cur[1];
            List<Integer> unlock= new ArrayList<>();
            List<Integer> remain = new ArrayList<>();

            for (int i = 0; i < n; i++){
                if (((node & graph[i]) == graph[i])&& ((node & p2[i]) == 0)){
                    if (outDegree[i] > 0){
                        unlock.add(p2[i]);
                    } else {
                        remain.add(p2[i]);
                    }
                }
            }

            int l1 = unlock.size();

            if (l1 <= k){
                node += sum(unlock, l1) + sum(remain, k - l1);
                if (node == goal)
                    return steps + 1;

                if (!visited[node]){
                    q.add(new int[]{node, steps + 1});
                    visited[node] = true;
                }
            } else {
                List<int[]> comb = new ArrayList<>();
                findCombinations(comb, new int[k], 0, 0, unlock);

                for (int[] c : comb){
                    int sum = 0;

                    for (int i = 0; i < c.length; i++)
                        sum += c[i];

                    sum += node;

                    if (sum == goal)
                        return steps + 1;

                    if (!visited[sum]){
                        q.add(new int[]{sum, steps + 1});
                        visited[sum] = true;
                    }
                }
            }
        }

        return -1;
    }

    private void findCombinations(List<int[]> comb, int[] data, int start,
                                  int index, List<Integer> unlock){
        if (index == data.length){
            comb.add(data.clone());
        } else if (start < unlock.size()) {
            data[index] = unlock.get(start);
            findCombinations(comb, data, start + 1, index + 1, unlock);
            findCombinations(comb, data, start + 1, index, unlock);
        }
    }

    private int sum(List<Integer> l, int n){
        int sum = 0;
        int size = l.size();

        for (int i = 0; i < n && i < size; i++)
            sum += l.remove(0);

        return sum;
    }
}