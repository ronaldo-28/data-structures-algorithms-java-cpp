class Solution {
    static int charDiff(String a, String b){
        int diff = 0;

        for (int i = 0; i<a.length(); i++){
            if (diff > 1) return diff;
            if (a.charAt(i) != b.charAt(i)){
                diff++;
            }
        }

        return diff;
    }

    static HashMap<String, List<String>> construct(String[] bank){
        HashMap<String, List<String>> graph = new HashMap<>();

        for (String s : bank){
            graph.put(s, new ArrayList<>());
        }

        for (int i =0; i<bank.length; i++){
            for (int j = i+1; j<bank.length; j++){
                if (charDiff(bank[i],bank[j]) == 1){
                    graph.get(bank[i]).add(bank[j]);
                    graph.get(bank[j]).add(bank[i]);
                }
            }
        }

        return graph;
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        HashMap<String, List<String>> graph = construct(bank);

        if (!graph.containsKey(startGene)){
            graph.put(startGene, new ArrayList<>());
            for (String s : bank){
                if (charDiff(startGene, s) == 1){
                    graph.get(startGene).add(s);
                    graph.get(s).add(startGene);
                }
            }
        }

        //BFS
        Queue<String> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        q.add(startGene);
        visited.add(startGene);

        int lvl = 0;

        while (!q.isEmpty()){
            int size = q.size();

            for (int i =0; i<size; i++){
                String s = q.remove();

                if (charDiff(s,endGene) == 0) return lvl;

                for (String b : graph.get(s)){
                    if (visited.add(b)){
                        q.add(b);
                    }
                }
            }

            lvl++;
        }

        return -1;

    }
}