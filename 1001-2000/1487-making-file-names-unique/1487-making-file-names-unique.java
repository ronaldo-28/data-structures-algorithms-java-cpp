class Solution {
    public String[] getFolderNames(String[] names) {
        int n = names.length;
        var map = new HashMap<String, Integer>(2*n+1);

        for(int i=0; i<n; ++i){
            String name = names[i];            
            int k = map.getOrDefault(name, 0);
            while(map.containsKey(name)) {
                k++;
                name = names[i] + "(" + k + ")";
            }
            
            map.put(name, 0);
            map.put(names[i], k);
            names[i] = name;
        }

        return names;        
    }
}