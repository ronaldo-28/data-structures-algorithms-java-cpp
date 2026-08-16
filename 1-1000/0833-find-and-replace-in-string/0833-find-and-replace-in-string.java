class Solution {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        // Map<Integer, Integer> table = new HashMap<>();
        int[] table = new int[s.length()];
        Arrays.fill(table, -1);
        for(int i = 0; i < indices.length; i++) {
            if(s.startsWith(sources[i], indices[i])) {
                // table.put(indices[i], i);
                table[indices[i]] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length();) {
            if(table[i] != -1) {
                // if a replacement was recorded before
                // sb.append(targets[table.get(i)]);
                sb.append(targets[table[i]]);
                // i += sources[table.get(i)].length();
                i += sources[table[i]].length();

            } else {
                sb.append(s.charAt(i));
                i++;
            }
        }

        return sb.toString();
    }
}