class Solution {
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Map<String, List<String>> al = new HashMap<>();
        for (List<String> edge : synonyms) {
            String u = edge.get(0);
            String v = edge.get(1);

            al.putIfAbsent(u, new ArrayList<>());
            al.get(u).add(v);

            al.putIfAbsent(v, new ArrayList<>());
            al.get(v).add(u);
        }

        // System.out.println(al);

        Set<String> visited = new HashSet<>();
        Map<String, List<String>> groups = new HashMap<>();
        Deque<String> stack = new ArrayDeque<>();
        for (String s : al.keySet()) {
            if (visited.contains(s)) continue;
            visited.add(s);

            List<String> grp = new ArrayList<>();
            
            grp.add(s);
            groups.put(s, grp);
        

            for (String nb : al.get(s)) {
                stack.push(nb);
            }

            while (!stack.isEmpty()) {
                String c = stack.poll();
                if (visited.contains(c)) continue;
                visited.add(c);
                grp.add(c);
                groups.put(c, grp);
                for (String nbh : al.get(c)) {
                    if (!visited.contains(nbh)) stack.push(nbh);
                }
            }
            Collections.sort(grp);
        }


        List<String> ans = new ArrayList<>();
        build(ans, new StringBuilder(), text.split(" "), 0, groups);
        return ans;
    }

    public void build(List<String> ans, StringBuilder s,
                      String[] words, int i, 
                      Map<String, List<String>> grps) {
        int j = i;
        int oldLen = s.length();
        while (!grps.containsKey(words[j])) {
            s.append(words[j]);
            j++;

            if (j == words.length) {
                ans.add(s.toString());
                s.setLength(oldLen);
                return;
            } else {
                s.append(" ");
            }
        }

        oldLen = s.length();

        
        if (j == words.length - 1) {
            for (String syn : grps.get(words[j])) {
                s.append(syn);
                ans.add(s.toString());
                s.setLength(oldLen);
            }
        } else {
            for (String syn : grps.get(words[j])) {
                s.append(syn);
                s.append(" ");
                build(ans, s, words, j + 1, grps);
                s.setLength(oldLen);
            }
        }

    }

}