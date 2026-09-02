class Solution {
    public boolean checkContradictions(List<List<String>> equations, double[] values) {
        
        HashMap<String, Pair<String, Double>> parents = new HashMap<>();

        for(int i=0; i<equations.size(); i++){
            if(union(parents, equations.get(i).get(0), equations.get(i).get(1), values[i])) 
            return true;
        }
        return false;

    }

    public Pair<String, Double> findRoot(Map<String, Pair<String, Double>> map, String key){
        double value = 1;
        if(!map.containsKey(key)){
            map.put(key, new Pair(key, 1.0d));
            return new Pair(key, 1.0d);
        }

        while(map.get(key).getKey() != key){
            value *= map.get(key).getValue();
            key = map.get(key).getKey();
        }

        return new Pair<String, Double>(key, value);
    }

    public boolean union(Map<String, Pair<String, Double>> map, String a, String b, double value){
        Pair<String, Double> root1 = findRoot(map, a); // find root1/a
        Pair<String, Double> root2 = findRoot(map, b); // find root2/b

        if(root1.getKey() != root2.getKey()){
            double rootVal = root1.getValue() *value* (1.0d/root2.getValue()); // find root1/root2 
            map.put(root2.getKey(), new Pair(root1.getKey(), rootVal));
            return false;
        }
        
        double rootVal = (1.0d/root1.getValue())*root2.getValue(); // find a/b should be equal to provided a/b (value)
        return !(Math.abs(rootVal - value) <= Math.pow(10, -5));

    }
}