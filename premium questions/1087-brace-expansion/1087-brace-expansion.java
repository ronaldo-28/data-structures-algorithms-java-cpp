class Solution {
    public String[] expand(String s) {
        List<List<Character>> groups = genGroups(s);
        List<String> result = new ArrayList<>();

        backtrack(groups, 0, new StringBuilder(), result);

        return result.toArray(new String[0]);
    }

    public List<List<Character>> genGroups(String s) {
        List<List<Character>> groups = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);
            List<Character> group = new ArrayList<>();

            if (c == '{') {
                i++;
                while (i < s.length() && s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        group.add(s.charAt(i));
                    }
                    i++;
                }
                i++;
                groups.add(group);
            } else if (Character.isLetter(c)) {
                group.add(c);
                groups.add(group);
                i++;
            }
        }

        for (List<Character> group : groups) {
            Collections.sort(group);
        }
        return groups;
    }

    public void backtrack(List<List<Character>> groups, int groupIdx, StringBuilder currSb, List<String> result) {
        if (groupIdx == groups.size()) {
            result.add(currSb.toString());
            return;
        }

        List<Character> group = groups.get(groupIdx);

        for (Character c : group) {
            currSb.append(c);
            backtrack(groups, groupIdx + 1, currSb, result);
            currSb.deleteCharAt(currSb.length() - 1);
        }
    }
}
/**
acdf,acef,bcdf,bcef

 */