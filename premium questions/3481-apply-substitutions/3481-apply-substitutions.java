
class Solution {
    public String applySubstitutions(List<List<String>> replacements, String text) {
        var unresolvedMap = new String[26];
        var isResolved = new boolean[26];
        for (var replacement : replacements) {
            unresolvedMap[replacement.get(0).charAt(0)-'A'] = replacement.get(1);
        }
        return applySubstitutions(unresolvedMap, isResolved, text);
    }

    private String applySubstitutions(String[] unresolvedMap, boolean[] isResolved, String s) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '%') {
                int key = s.charAt(i+1)-'A';
                if (!isResolved[key]) {
                    unresolvedMap[key] = applySubstitutions(unresolvedMap, isResolved, unresolvedMap[key]);
                    isResolved[key] = true;
                }
                result.append(unresolvedMap[key]);
                i = i + 3; // move past closing %
                continue;
            }
            result.append(s.charAt(i));
            i++;
        }
        return result.toString();
    }
}