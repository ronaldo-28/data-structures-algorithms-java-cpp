class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, Integer> idmap = new HashMap<>();
        UnionFind uf = new UnionFind(equations.size() * 2);
        int id = 0;
        for(int i = 0; i<equations.size(); i++){
            if(!idmap.containsKey(equations.get(i).get(0))){
                idmap.put(equations.get(i).get(0), id++);
            }
            if(!idmap.containsKey(equations.get(i).get(1))){
                idmap.put(equations.get(i).get(1), id++);
            }
            uf.union(idmap.get(equations.get(i).get(0)), idmap.get(equations.get(i).get(1)), values[i]);
        }
        int qs = queries.size();
        double[] res = new double[qs];
        for(int i = 0; i<qs; i++){
            if(idmap.containsKey(queries.get(i).get(0)) && idmap.containsKey(queries.get(i).get(1)))
                res[i] = uf.isConnect(idmap.get(queries.get(i).get(0)), idmap.get(queries.get(i).get(1)));
            else res[i] = -1.0d;
        }
        return res;
    }

    private class UnionFind {
        int[] parent;
        double[] weight;

        public UnionFind(int n){
            parent = new int[n];
            weight = new double[n];
            for(int i = 0; i<n; i++){
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        private int find(int i){
            if(parent[i] != i){
                int origin = parent[i];
                parent[i] = find(parent[i]);
                weight[i] *= weight[origin];
            }
            return parent[i];
        }

        private void union(int u, int v, double val){
            int rootU = find(u);
            int rootV = find(v);

            if(rootU == rootV){
                return;
            }
            parent[rootU] = rootV;
            weight[rootU] = weight[v] * val / weight[u];
        }

        private double isConnect(int u, int v){
            int rootU = find(u);
            int rootV = find(v);

            if(rootU == rootV){
                return weight[u] / weight[v];
            }
            return -1.0d;
        }
    }
}