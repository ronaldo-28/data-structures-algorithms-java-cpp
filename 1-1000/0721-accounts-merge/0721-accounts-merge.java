import java.util.AbstractList;

class DisjointSet {
    int[] parent, rank;
    int n;

    DisjointSet(int n) {
        this.n = n;
        rank = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int i) {
        int root = parent[i];
        if (parent[root] != root) {
            return parent[i] = find(root);
        }
        return root;
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootY] < rank[rootX]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX] += 1;
        }
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        return new AbstractList<>(){
            List<List<String>> result;
            @Override
            public List<String> get(int index){
                initializeResult();
                return result.get(index);
            }

            @Override
            public int size(){
                initializeResult();
                return result.size();
            }

            private void initializeResult(){
                DisjointSet ds = new DisjointSet(accounts.size());
                Map<String, Integer> emailAccountMap = new HashMap<>();
                for(int i = 0 ; i < accounts.size() ; i++){
                    int accountIndex = i;
                    for(int j = 1 ; j < accounts.get(i).size() ; j++){
                        String email = accounts.get(accountIndex).get(j);
                        if(emailAccountMap.containsKey(email)){
                            ds.union(emailAccountMap.get(email), i);
                        } else {
                            emailAccountMap.put(email, accountIndex);
                        }
                    }
                }

                result = new ArrayList<>();
                for(int i = 0 ; i < accounts.size() ; i++){
                    result.add(new ArrayList<>());
                }
                for(Map.Entry<String, Integer> entry : emailAccountMap.entrySet()){
                    int accountIndex = ds.find(entry.getValue());
                    result.get(accountIndex).add(entry.getKey());
                }
                
                for(int i = 0 ; i < result.size() ; i++){
                    List<String> resultItem = result.get(i);
                    if(!resultItem.isEmpty()){
                        Collections.sort(resultItem);
                        resultItem.add(0, accounts.get(i).get(0));
                    }
                }
                result.removeIf(List::isEmpty);            
            }

        };
    }

}