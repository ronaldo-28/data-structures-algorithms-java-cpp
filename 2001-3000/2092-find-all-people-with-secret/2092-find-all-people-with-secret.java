class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        parent[firstPerson] = 0;

        int maxTime = 0;
        for (int[] meet : meetings) {
            maxTime = Math.max(maxTime, meet[2]);
        }

        List<int[]>[] timeArray = new List[maxTime + 1];
        for (int[] meet : meetings) {
            if (timeArray[meet[2]] == null) {
                timeArray[meet[2]] = new ArrayList<>();
            }
            timeArray[meet[2]].add(new int[]{meet[0], meet[1]});
        }

        for (int i = 1; i < timeArray.length; i++) {
            if (timeArray[i] != null) {
                for (int j = 0; j < timeArray[i].size(); j++) {
                    int u = timeArray[i].get(j)[0];
                    int v = timeArray[i].get(j)[1];
                    union(u, v, parent);
                }
                for (int j = 0; j < timeArray[i].size(); j++) {
                    int u = timeArray[i].get(j)[0];
                    int v = timeArray[i].get(j)[1];
                    if (find(u, parent) != 0) {
                        parent[u] = u;
                    }
                    if (find(v, parent) != 0) {
                        parent[v] = v;
                    }
                }
            }
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == 0) {
                results.add(i);
            }
        }

        return results;
    }

    private void union(int u, int v, int[] parent) {
        int uRoot = find(u, parent);
        int vRoot = find(v, parent);
        if (uRoot < vRoot) {
            parent[vRoot] = uRoot;
        } else {
            parent[uRoot] = vRoot;
        }
    }

    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }
}